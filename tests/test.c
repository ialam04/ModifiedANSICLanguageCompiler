/* Multi-line comment testing
   Multiple lines
*/

// Single line comment
var count = 42;
var message = "Test \"escaped\" strings";
var ch = '\n';
var result = 0;

fun add(x, y) {
    return x + y;
}

fun factorial(n) {
    if (n <= 1) {
        return 1;
    }
    return n * factorial(n - 1);
}

fun main() {
    var i = 0;
    var sum = 0;
    var arr[10];

    // Initialize array
    while (i < 10) {
        arr[i] = i * 2;
        i = i + 1;
    }

    // Test for loop
    for (i = 0; i < 5; i = i + 1) {
        sum = sum + arr[i];
    }

    // Test compound expressions
    result = add(sum, (15 * 2 + 3) / 2 - 5);

    if (result >= 100) {
        printf("Large result: %d\n", result);
    } else if (result > 50) {
        printf("Medium result: %d\n", result);
    } else {
        printf("Small result: %d\n", result);
    }

    // Test escape sequences
    printf("Special chars: \t\b\n\r\\\"\'\n");
    
    return factorial(5);
}