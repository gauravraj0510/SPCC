%{
  #undef yywrap
  #define yywrap() 1
#include<stdio.h>
int len;
%}
%%
[a-z]+ {
len = strlen(yytext);
printf("The length of given input is: %d", len);
}
%%
int main(){
yylex();
return 0;
}