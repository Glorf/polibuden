package put.ai.games.uctplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;

public class UCTPlayer extends Player {
    public static void main(String[] args) {}

    /**
     * SNORT player based on UCT-RAVE algorithm
     * Bibliography:
     * Kocsis L., Szepesvári C., Bandit based Monte-Carlo Planning (2006)
     * Gelly S., Silver D., Combining Online and Offline Knowledge in UCT (2007)
     * Silver D., Reinforcement Learning and Simulation-Based Search in Computer Go (2009)
     * Gelly S., Silver D., Monte-Carlo tree search and rapid action value estimation in computer Go (2010)
     * S. Mirsoleimani, J. van den Herik, A. Plaat, J. Vermaseren, A Lock-free Algorithm for Parallel MCTS (2018)
     */

    private Random random;

    //TWEAKABLE ARGS
    private static double explorationConstant = Math.sqrt(2.0);
    private static double RAVEBias = 1e-9;
    private static double epsilon = 1e-6;

    @Override
    public String getName() {
        return "Michal Bien 132191 Martyna Maciejewska 132273";
    }


    @Override
    public Move nextMove(Board b) {
        long timeout = System.nanoTime() + getTime()*2/3*1000000; //Maximum time when UCT should stop

        if(random == null) { //initialize class
            this.random = new Random();
        }

        Color player = getColor();

        Node rootNode = new Node();

        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        for(int i=0; i<4; i++) {
            Thread t = new Thread(new Worker(b, timeout, rootNode, player));
            t.setPriority(Thread.MAX_PRIORITY);
            t.start();
        }

        while(timeout > System.nanoTime()) {
            try {
                Thread.sleep(1);
            }catch (Exception e){}
        }

        return bestChild(rootNode).getMove();
    }

    private Node bestChild(Node root) { //We set the best node as the most visited one (to be subject of change?)
        int mostPlays = Integer.MIN_VALUE;
        ArrayList<Node> bestNodes = new ArrayList<>();

        for (Node node : root.getChildren()) {
            int scores[] = node.get();
            if (scores[1] > mostPlays) { //node is considered better than any other considered, dropping best list
                bestNodes.clear();
                bestNodes.add(node);
                mostPlays = scores[1];
            } else if (scores[1] == mostPlays) { //node is one of the best nodes at this time, add to best list
                bestNodes.add(node);
            }
        }

        return bestNodes.get(random.nextInt(bestNodes.size())); //if there is more than one node considered best, return any
    }

    /**
     * Traverse tree using UCT
     * @param state board state after node's move (or initial state for root node)
     * @param node is root node
     */
    private Node select(Node node, StatefulBoard state) {
        Node current = node;

        while(current.isFullyExpanded()) {
            int score[] = current.get();

            Node bestNode = current;
            double best = Double.NEGATIVE_INFINITY;
            for(Node child: current.getChildren()) {
                double uct = child.UCT(score[1]);
                if(uct > best) {
                    bestNode = child;
                    best = uct;
                }
            }

            current = bestNode;
            state.doMove(current.getMove());
        }

        return current;
    }

    private Node expand(Node node, StatefulBoard state) {
        if(state.getWinner() == null) {
            node.createChildren(state);

            Node child = node.addChild();
            if(!child.equals(node)) {
                state.doMove(child.getMove());
                return child;
            }
        }

        return node;
    }

    private int playout(StatefulBoard state, ArrayList<Move> memory) {
        while(state.getWinner() == null) {
            List<Move> moves = state.getMoves();
            Move move = moves.get(random.nextInt(moves.size()));
            memory.add(move);
            state.doMove(move);
        }

        if(state.getWinner() == getColor())
            return 1;
        else
            return 0;
    }

    private void backup(Node node, int score, ArrayList<Move> memory) {
        Node current = node;
        while(current != null) {
            current.set(score);

            int s = current.getChildren().size();
            for (int i = 0; i < s; i++) {
                Node child = current.getChildren().get(i);
                if (memory.contains(child.getMove()))
                    current.setRave(score, child.move);
            }

            current = current.getParent();
        }
    }

    private class Worker implements Runnable { //Thread that is running concurrently
        private Board board;
        private long timeout;
        private Node rootNode;
        private Color player;

        Worker(Board board, long timeout, Node rootNode, Color player) {
            this.board = board;
            this.rootNode = rootNode;
            this.player = player;
            this.timeout = timeout;
        }

        @Override
        public void run() {
            while (System.nanoTime() < timeout) { //keep a window of time to be sure worker will stop on time
                StatefulBoard playBoard = new StatefulBoard(board, player);
                Node selected = select(rootNode, playBoard);
                selected = expand(selected, playBoard);
                ArrayList<Move> memory = new ArrayList<>();
                int score = playout(playBoard, memory);
                backup(selected, score, memory);
            }
        }

    }

    /**
     * Tree node
     */
    class Node {
        private ArrayList<Node> children; //Child nodes
        private Node parent; //Parent node

        private Move move; //Move to perform on parent's board to get into this node state
        private AtomicInteger unexpandedChildren; //number of non-expanded-yet children
        private AtomicLong playScore; //plays and score

        private AtomicBoolean isParent;
        private AtomicBoolean isExpandable;
        private AtomicBoolean isFullyExpanded;
        private ConcurrentHashMap<Move, AtomicLong> raveMap;

        /**
         * Node constructor
         */
        Node() {
            this.children = new ArrayList<>();
            this.isParent = new AtomicBoolean(false);
            this.isFullyExpanded = new AtomicBoolean(false);
            this.isExpandable = new AtomicBoolean(false);
            this.playScore = new AtomicLong(0);
            this.unexpandedChildren = new AtomicInteger(-1);
            this.raveMap = new ConcurrentHashMap<>();
        }

        /**
         * Generate node children given board state
         * @param board is an actual gameplay board that represents node's state
         */
        void createChildren(StatefulBoard board) {
            if(!isParent.getAndSet(true)) {
                List<Move> moves = board.getMoves();
                for (Move move : moves) {
                    Node n = new Node();
                    n.move = move;
                    n.parent = this;
                    children.add(n);
                }
                unexpandedChildren.set(children.size());
                isExpandable.set(true);
            }
        }

        Node addChild() {
            if(isExpandable.get()) {
                int index = unexpandedChildren.decrementAndGet();
                if(index == 0)
                    isFullyExpanded.set(true);
                if(index < 0) return this;
                else return children.get(index);
            }
            else
                return this;
        }

        Boolean isFullyExpanded() {
            return isFullyExpanded.get();
        }

        int[] get() {
            long scores = playScore.get();
            int result[] = new int[2];
            result[0] = (int)(scores>>32);
            result[1] = (int)scores;

            return result;
        }

        void set(int score) {
            long added = (long)score<<32;
            added+=1;

            playScore.getAndAdd(added);
        }

        int[] getRave(Move move) {
            long scores = raveMap.computeIfAbsent(move, f -> new AtomicLong(0)).get();
            int result[] = new int[2];

            result[0] = (int)(scores>>32);
            result[1] = (int)scores;

            return result;
        }

        void setRave(int score, Move move) {
            long added = (long)score<<32;
            added+=1;

            raveMap.computeIfAbsent(move, f -> new AtomicLong(0)).getAndAdd(added);
        }

        double UCT(int n) {
            int wn[] = get();
            double wi = (double)wn[0];
            double ni = (double)wn[1]+epsilon;
            double bias = random.nextDouble()*epsilon;
            int rv[] = parent.getRave(move);
            double wih = (double)rv[0] + 1;
            double nih = (double)rv[1] + 1;
            double beta = nih/(ni+nih+(4*Math.pow(RAVEBias,2)*ni*nih)); //David Silver (2009) formula
            //System.out.println(wih/nih);

            return ((1-beta)*(wi/ni)) + beta*(wih/nih) + (explorationConstant * Math.sqrt(Math.log(n + 1) / ni)) + bias;
        }

        ArrayList<Node> getChildren() {
            return children;
        }

        Node getParent() {
            return parent;
        }

        Move getMove() {
            return move;
        }
    }

    class StatefulBoard {
        private Color currentColor;
        private Board board;

        StatefulBoard(Board board, Color color) {
            this.board = board.clone();
            this.currentColor = color;
        }

        void doMove(Move move) {
            board.doMove(move);
            currentColor = Player.getOpponent(currentColor);
        }

        List<Move> getMoves() {
            return board.getMovesFor(currentColor);
        }

        Color getWinner() {
            return board.getWinner(currentColor);
        }
    }
}
