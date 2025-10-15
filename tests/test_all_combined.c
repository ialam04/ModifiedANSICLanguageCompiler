/* Combined test file with declarations, statements, and expressions */

/* Variable declarations */
var int counter = 0;
var float result;
var char* message = "Hello";

/* Function definition with complex body */
fun int process(int x, int y) {
    var int temp;
    var int sum = 0;
    
    /* Assignment expressions */
    temp = x + y;
    sum += temp;
    
    /* Control flow */
    if (x > 0) {
        while (temp > 0) {
            sum = sum * 2;
            temp--;
        }
    } else {
        for (temp = 0; temp < 10; temp++)
            sum = sum + 1;
    }
    
    /* Function call */
    helper(sum);
    
    /* Return with expression */
    return sum * x - y;
}

/* Another function */
fun void helper(int val) {
    var int i;
    
    for (i = 0; i < val; i++) {
        if (i % 2 == 0)
            continue;
        process(i, i + 1);
    }
}

/* Struct with usage */
struct node {
    int data;
    struct node* next;
};

fun void useStruct() {
    var struct node n;
    n.data = 42;
    n.next = &n;
}

/* Enum with usage */
enum state {
    INIT = 0,
    RUNNING = 1,
    STOPPED = 2
};

fun int getState() {
    var enum state s;
    s = RUNNING;
    return s;
}

/* Array operations */
fun void arrayTest() {
    var int arr[10];
    var int i;
    
    for (i = 0; i < 10; i++)
        arr[i] = i * i;
    
    result = arr[5];
}

/* Pointer operations */
fun void pointerTest() {
    var int x = 10;
    var int* ptr;
    
    ptr = &x;
    *ptr = 20;
    x = *ptr + 5;
}
