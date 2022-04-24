#include <stdio.h>
#include <conio.h>
#include <string.h>
#include <stdlib.h>
typedef enum
{
    true,
    false
} bl;
bl Separator(char ch)
{
    if (ch == ' ' || ch == '+' || ch == '-' || ch == '*' ||
        ch == '/' || ch == ',' || ch == ';' || ch == '>' ||
        ch == '<' || ch == '=' || ch == '(' || ch == ')' ||
        ch == '[' || ch == ']' || ch == '{' || ch == '}')
        return (true);
    return (false);
}
bl Operator(char ch)
{
    if (ch == '+' || ch == '-' || ch == '*' ||
        ch == '/' || ch == '>' || ch == '<' ||
        ch == '=')
        return (true);
    return (false);
}
bl validIdentifier(char *str)
{
    if (str[0] == '0' || str[0] == '1' || str[0] == '2' ||
        str[0] == '3' || str[0] == '4' || str[0] == '5' ||
        str[0] == '6' || str[0] == '7' || str[0] == '8' ||
        str[0] == '9' || Separator(str[0]) == true)
        return (false);
    return (true);
}
bl Format(char *str)
{
    if (!strcmp(str, "%c") || !strcmp(str, "%s") || !strcmp(str, "%i") || !strcmp(str, "%o") || !strcmp(str, "%d") || !strcmp(str, "%e") || !strcmp(str, "%x"))
        return (true);
    return (false);
}
bl Func(char *str)
{
    if (!strcmp(str, "main") || !strcmp(str, "printf") || !strcmp(str, "scanf") ||
        !strcmp(str, "gets") || !strcmp(str, "puts"))
        return (true);
    return (false);
}
bl Keyword(char *str)
{
    if (!strcmp(str, "if") || !strcmp(str, "else") || !strcmp(str, "while") || !strcmp(str, "do") ||
        !strcmp(str, "break") || !strcmp(str, "continue") || !strcmp(str, "int") ||
        !strcmp(str, "double") || !strcmp(str, "float") || !strcmp(str, "return") ||
        !strcmp(str, "char") || !strcmp(str, "case") || !strcmp(str, "sizeof") || !strcmp(str, "long") ||
        !strcmp(str, "short") || !strcmp(str, "typedef") || !strcmp(str, "switch") ||
        !strcmp(str, "unsigned") || !strcmp(str, "void") || !strcmp(str, "static") || !strcmp(str, "struct") ||
        !strcmp(str, "goto") || !strcmp(str, "auto") || !strcmp(str, "volatile") || !strcmp(str, "for") ||
        !strcmp(str, "const") || !strcmp(str, "default") || !strcmp(str, "signed") || !strcmp(str, "enum") ||
        !strcmp(str, "register") || !strcmp(str, "extern") || !strcmp(str, "union"))
        return (true);
    return (false);
}
bl Integer(char *str)
{
    int i, len = strlen(str);
    if (len == 0)
        return (false);
    for (i = 0; i < len; i++)
    {
        if (str[i] != '0' && str[i] != '1' && str[i] != '2' && str[i] != '3' && str[i] != '4' && str[i] != '5' && str[i] != '6' && str[i] != '7' && str[i] != '8' && str[i] != '9' || (str[i] == '-' && i > 0))
            return (false);
    }
    return (true);
}
bl FloatNumber(char *str)
{
    int i, len = strlen(str);
    bl Decimal = false;
    if (len == 0)
        return (false);
    for (i = 0; i < len; i++)
    {
        if (str[i] != '0' && str[i] != '1' && str[i] != '2' && str[i] != '3' && str[i] != '4' && str[i] != '5' && str[i] != '6' && str[i] != '7' && str[i] != '8' && str[i] != '9' && str[i] != '.' ||
            (str[i] == '-' && i > 0))
            return (false);
        if (str[i] == '.')
            Decimal = true;
    }
    return (Decimal);
}
char *subString(char *str, int left, int right)
{
    int i;
    char *subStr = (char *)malloc(sizeof(char) * (right - left + 2));
    for (i = left; i <= right; i++)
        subStr[i - left] = str[i];
    subStr[right - left + 1] = '\0';
    return (subStr);
}
void lexeme(char *str)
{
    int left = 0, right = 0;
    int len = strlen(str);
    while (right <= len && left <= right)
    {
        if (Separator(str[right]) == false)
            right++;
        if (Separator(str[right]) == true && left == right)
        {
            if (Operator(str[right]) == true)
                printf("'%c':Operator\n", str[right]);
            right++;
            left = right;
        }
        else if (Separator(str[right]) == true && left != right || (right == len && left !=
                                                                                        right))
        {
            char *subStr = subString(str, left, right - 1);
            if (Keyword(subStr) == true)
                printf("'%s':keyword\n", subStr);
            else if (Func(subStr) == true)
                printf("'%s':Function\n", subStr);
            else if (Format(subStr) == true)
                printf("'%s':Format Specifier\n", subStr);
            else if (Integer(subStr) == true)
                printf("'%s':Integer\n", subStr);
            else if (FloatNumber(subStr) == true)
                printf("'%s':Floating Number\n", subStr);
            else if (validIdentifier(subStr) == true && Separator(str[right - 1]) == false)
                printf("'%s':Valid Identifier\n", subStr);
            else if (validIdentifier(subStr) == false && Separator(str[right - 1]) == false)
                printf("'%s':Invalid Identifier\n", subStr);
            left = right;
        }
    }
    return;
}
void main()
{
    char str[200];
    printf("Enter a string for lexical analysis:");
    gets(str);
    lexeme(str);
    getch();
}

// OUTPUT
// Enter a string for lexical analysis:b=2*c+a;printf(b);
// 'b':Valid Identifier
// '=':Operator
// '2':Integer
// '*':Operator
// 'c':Valid Identifier
// '+':Operator
// 'a':Valid Identifier
// 'printf':Function
// 'b':Valid Identifier