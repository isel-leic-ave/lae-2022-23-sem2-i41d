// Point.c : Defines the exported functions
//
#include "Point.h"
#include <math.h>
#include <stdio.h>
#include <stdlib.h>

void Point_init(Point* p, int x, int y) {
	p->x = x;
	p->y = y;
}

Point* Point_new(int x, int y) {
	Point* p = (Point*) malloc(sizeof(Point));
	Point_init(p, x, y);
	return p;
}

void Point_delete(Point* p) {
	free(p);
}

double Point_module(Point* p) {
	return sqrt((double) p->x * p->x + p->y * p->y);
}

void Point_print(Point* p) {
	printf("Point v2 (x = %d, y = %d)\n", p->x, p->y);
}
