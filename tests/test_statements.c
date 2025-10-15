/* Test file for statement parsing */

{
    /* Compound statement with declarations and statements */
    var int x;
    var int y;
    x = 10;
    y = 20;
    
    /* If statement */
    if (x > 5)
        y = 100;
    
    /* If-else statement */
    if (x < 0)
        y = -1;
    else
        y = 1;
    
    /* While loop */
    while (x > 0) {
        x = x - 1;
        y = y + 1;
    }
    
    /* Do-while loop */
    do {
        x = x + 1;
    } while (x < 10);
    
    /* For loop */
    for (x = 0; x < 10; x = x + 1)
        y = y * 2;
    
    /* Labeled statement */
    start: x = 0;
    
    /* Goto statement */
    goto start;
    
    /* Break and continue */
    while (x < 100) {
        if (x == 50)
            break;
        if (x % 2 == 0)
            continue;
        x = x + 1;
    }
    
    /* Return statements */
    return;
    return x + y;
    
    /* Expression statement */
    foo(x, y);
    x++;
    
    /* Case and default (in switch context) */
    case 1: x = 1;
    case 2: x = 2;
    default: x = 0;
}
