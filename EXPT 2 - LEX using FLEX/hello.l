%{
 #undef yywrap
 #define yywrap() 1
 char name[20];
%}

%%

[\n] {
 printf("Hello %s",name);
}

%%

void main()
{
 printf("Enter your name:");
 scanf("%s",name);
 yylex();
}

// OUTPUT
// Enter your name:Gaurav
// Hello Gaurav