/* Test file with declarations only */

/* Variable declarations */
var int count = 42;
var char* message = "Hello World";
var int result = 0;

/* String literals */
var char* str1 = "";
var char* str2 = "Simple string";
var char* str3 = "String with \"quotes\" inside";

/* Character literals */
var char ch1 = 'a';
var char ch2 = 'Z';
var char ch3 = '5';
var char esc1 = '\n';
var char esc2 = '\t';

/* Integer expressions */
var int expr1 = 10 + 20;
var int expr2 = 5 * 6;
var int expr3 = (100 - 25) / 3;

/* Pointers */
var int* ptr1;
var char* ptr2 = "test";
var int** doublePtr;

/* Arrays */
var int[10] arr1;
var char[256] buffer;
var int*[5] ptrArray;

/* Typedef declarations */
typedef int Integer;
typedef char* String;
typedef int[10] IntArray;

/* Struct declarations */
struct Point {
  int x;
  int y;
}

struct Person {
  char* name;
  int age;
  int height;
}

/* Union declarations */
union Data {
  int i;
  char c;
  float f;
}

/* Enum declarations */
enum Color { RED, GREEN, BLUE }
enum Status { OK, ERROR, PENDING }

/* Function declarations (prototypes) */
fun int add(int, int);
fun void printMessage(char*);
fun int* allocate(int);

/* Function definitions */
fun int multiply(int x, int y) {
  return x * y;
}

fun void doNothing() {
}
