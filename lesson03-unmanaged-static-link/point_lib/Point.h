// This  is exported from the Point.lib

typedef struct point Point;

struct point {
    int x, y;
};

void Point_init(Point* p, int x, int y);

Point* Point_new(int x, int y);

void Point_delete(Point* p);

double Point_module(Point* p);

void Point_print(Point* p);
