"""
    The module is used to resolve Lumberjack Challenge problem.
    It gains humble amount of 16 points, while the creator still has no idea how to improve it.

    My code passes all PEP8 tests, pylint scores it at 5.88/10
    It's not 10/10, because pylint forces usage of variable names more than 3 characters long
    (which is terribly inconvenient when you use x and y coordinates), and some other
    stuff that "is not a bug, it's a feature".
    Please, tell me if that's enough, or maybe I should make it even more "standard-compilant"

    html documentation (pydoc's) is also accesible at: https://micbie.pl/optilio_best.html
"""

from operator import itemgetter

def sumtreefall(trees, x, y, direction, cut, first):
    """
        Calculates recursively the sum value of chain of trees, that can fall on each other

        Args:
            trees: dictionary containing trees
            x: current lumberjack x position
            y: current lumberjack y position
            direction: which direction to cut in
            cut: if True, cut trees will be popped out trees dictionary
            first: set True, if you call this function from outside

        Returns:
            integer, calculated value of called tree + trees that will fall down due to this tree
    """
    treeval = trees[x, y]['value']*trees[x, y]['height']*trees[x, y]['thickness']
    yrweight = trees[x, y]['weight']*trees[x, y]['height']*trees[x, y]['thickness']
    for i in range(1, trees[x, y]['height'] + 1):
        tx, ty = 0, 0
        if direction == 'left' and (x+i, y) in trees:
            tree = trees[x+i, y]
            tx, ty = x+i, y
        elif direction == 'right' and (x-i, y) in trees:
            tree = trees[x-i, y]
            tx, ty = x-i, y
        elif direction == 'up' and (x, y+i) in trees:
            tree = trees[x, y+i]
            tx, ty = x, y+i
        elif direction == 'down' and (x, y-i) in trees:
            tree = trees[x, y-i]
            tx, ty = x, y-i
        else:
            continue
        if tree['weight']*tree['height']*tree['thickness'] < yrweight:
            if cut and not first:
                trees.pop((x, y), None)
            else: first = False
            return treeval+sumtreefall(trees, tx, ty, direction, cut, first)
    return 0

def defallerate_trees(t, n, trees, tlist):
    """
       This weird-named function uses sumtreefall to calculate most worth falls of trees.
       Then it performs a cut, removes fallen trees, and adds their value to the sum value
       of the first tree

        Args:
            t: number of trees
            n: matrix size
            trees: dictionary containing trees
            tlist: list containing dictionaries of trees, sorted by decreasing tree value ratio

        Returns:
            null: manipulates on tlist, trees objects
    """
    for x in range(0, min(t, n)):
        for y in range(0, min(t, n)):
            if (x, y) in trees:
                chosendirect = 'left'
                maxval = 0
                tmp = 0
                for d in ['up', 'down', 'left', 'right']:
                    tmp = sumtreefall(trees, x, y, d, False, True)
                    if tmp > maxval:
                        chosendirect = d
                        maxval = tmp
                sumtreefall(trees, x, y, chosendirect, True, True)
                trees[x, y]['fullval'] = maxval
                trees[x, y]['ratio'] = maxval/(x+y+trees[x, y]['thickness'])
                trees[x, y]['cut'] = chosendirect
                h = trees[x, y]['height']
                d = trees[x, y]['thickness']
                c = trees[x, y]['weight']
                p = trees[x, y]['value']
                tlist.append({'x': x, 'y': y, 'height': h, 'thickness': d, 'weight': c, 'value': p,
                              'cut': 'left', 'fullval': p*h*d, 'ratio': (p*h*d)/(x+y+d)})

def movexy(x, y, newx, newy):
    """
        Moves lumberjack, according to calculated road

        Args:
            x: current lumberjack x position
            y: current lumberjack y position
            newx: target lumberjack x position
            newy: target lumberjack y position

        Returns:
            null: function result is written on stdout
    """
    while x < newx:
        print("move right")
        x += 1
    while x > newx:
        print("move left")
        x -= 1
    while y < newy:
        print("move up")
        y += 1
    while y > newy:
        print("move down")
        y -= 1

def recalculate_trees(tlist, x, y):
    """
        Refreshes tlist, recalculates tree ratio and sorts list by decreasing ratio.

        Args:
            tlist: list containing dictionaries of trees, sorted by decreasing tree value ratio
            x: current lumberjack x position
            y: current lumberjack y position

        Returns:
            null: function manipulates on tlist object
    """
    for tree in tlist:
        tree['ratio'] = (tree['fullval']/(abs(tree['x']-x)+abs(tree['y']-y)+tree['thickness']))
    tlist = sorted(tlist, key=itemgetter('ratio'), reverse=True)

def solve(tlist, t):
    """
        Main solution loop

        Args:
            tlist: list containing dictionaries of trees, sorted by decreasing tree value ratio
            t: total time amount

        Returns:
           null
    """
    x = 0
    y = 0
    time = 0
    recalculate_trees(tlist, x, y)
    i = 0
    while i < len(tlist):
        tree = tlist[i]
        if time + tree['thickness'] + abs(tree['x']-x) + abs(tree['y']-y) > t:
            i += 1
            continue
        movexy(x, y, tree['x'], tree['y'])
        time += abs(tree['x']-x) + abs(tree['y']-y)
        x = tree['x']
        y = tree['y']
        if time + tree['thickness'] <= t:
            print("cut "+tree['cut'])
            time += tree['thickness']
            tlist.remove(tree)
            recalculate_trees(tlist, x, y)
            i = 0

def read_data():
    """
        Reads data from input, "defallerates" trees, and passes to solve

        Returns:
            tlist: list containing dictionaries of trees, sorted by decreasing tree value ratio
            t: total time amount
    """
    t, n, k = (int(x) for x in input().split())
    trees = {}
    tlist = []
    for i in range(k):
        x, y, h, d, c, p = (int(x) for x in input().split())
        trees[x, y] = {'height' : h, 'thickness' : d, 'weight' : c, 'value' : p,
                       'cut' : 'left', 'fullval': p*h*d, 'ratio': (p*h*d)/(x+y+d)}
    defallerate_trees(t, n, trees, tlist)
    return tlist, t

def main():
    """
        Main function
    """
    solve(*read_data())

if __name__ == "__main__":
    main()
