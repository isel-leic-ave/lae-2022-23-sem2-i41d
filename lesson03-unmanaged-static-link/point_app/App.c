#include "Point.h"
#include <stdio.h>

int main(int argc, char * argv[])
{
    Point* p = Point_new(5, 7);
    Point_print(p);
    double pModule = Point_module(p);
    printf("module = %f\n", pModule);
    printf("p.x = %d\n", p->x);

}