%{
    
 #undef yywrap
 #define yywrap() 1
 #include<stdio.h>

%}

%%

[0-9]+ {printf("Number");}
[a-zA-Z]+ {printf("word");}

%%
 int main(){
 yylex();
 return 0;
}