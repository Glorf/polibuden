#include <stdio.h>
#include <stdlib.h>
#define max(a,b) ((a) > (b) ? (a) : (b))

#define dj_imp 0.78995
#define rj_imp 0.91915
#define pj_imp 0.84335
#define dj2_imp 0.07813
#define rj2_imp 0.35071
#define pj2_imp 0.99893

typedef struct {
    int id;
    int p;
    int r;
    int d;
    double cost;
} s_process;

typedef struct {
    int timestamp;
    int *queue;
    int stack;
} s_cpu;

s_cpu create_cpu(int n) {
    s_cpu cpu;
    cpu.queue = malloc(sizeof(int)*n);
    cpu.stack = 0;
    cpu.timestamp = 0;
    return cpu;
}

void destroy_cpu(s_cpu cpu) {
    free(cpu.queue);
}

double cost(s_process * elem) {
    /*
     * Each of these should be double
     */

    double p = (double)elem->p*pj_imp;
    double d = (double)elem->d*dj_imp;
    double r = (double)elem->r*rj_imp;

    double p2 = ((double)elem->p) * ((double)elem->p) * pj2_imp;
    double d2 = ((double)elem->d) * ((double)elem->d) * dj2_imp;
    double r2 = ((double)elem->r) * ((double)elem->r) * rj2_imp;

    return p + d + r + p2 + d2 + r2;
}

int qualifier(const void *elem1, const void *elem2) {
    s_process *proc1 = ((s_process*)elem1);
    s_process *proc2 = ((s_process*)elem2);

   if(proc1->cost > proc2->cost)
       return 1;
   if(proc2->cost > proc1->cost)
       return -1;

    //They must be equal!
    return 0;
}

int main(int argc, char **argv) {
    if(argc!=3){
        printf("Podaj argumenty: input file i output file\n");
        return 0;
    }
    FILE *fp = fopen(argv[1], "r");

    int n;
    fscanf(fp,"%d\n", &n);
    s_process processes[n];
    for(int i=0;!feof(fp); i++) {
        s_process *elem = &processes[i];
        fscanf(fp, "%d %d %d\n", &elem->p, &elem->r, &elem->d);
        processes[i].id = i+1;
    }
    fclose(fp);

    for(int i=0; i<n; i++) {
        processes[i].cost = cost(&processes[i]);
    }

    qsort(processes, n, sizeof(s_process), qualifier);

    s_cpu processors[4];
    for(int i=0; i<4; i++) processors[i] = create_cpu(n);

    int cost = 0;

    for(int i=0; i<n; i++) {
        int act_processor = 0;
        s_cpu *act_cpu = &processors[act_processor];
        for(int j=0; j<4; j++) {
            if(act_processor!=j && act_cpu->timestamp > processors[j].timestamp) {
                act_processor = j;
                act_cpu = &processors[act_processor];
            }
        }


        act_cpu->queue[act_cpu->stack] = processes[i].id;
        act_cpu->stack++;
        act_cpu->timestamp = act_cpu->timestamp > processes[i].r? act_cpu->timestamp: processes[i].r; //Do we have to wait to start the job?
        act_cpu->timestamp += processes[i].p;

        cost += act_cpu->timestamp - processes[i].d > 0 ? act_cpu->timestamp-processes[i].d : 0; //How much does it cost?
    }


    FILE *fp2 = fopen(argv[2], "w");
    fprintf(fp2,"%d\n", cost);
    for(int i=0; i<4; i++) {
        for(int j=0; j<processors[i].stack; j++) {
            if(j!=0)
                fprintf(fp2, " ");
            fprintf(fp2, "%d", processors[i].queue[j]);
        }
        fprintf(fp2, "\n");
    }


    for(int i=0; i<4; i++) destroy_cpu(processors[i]);
    return 0;
}