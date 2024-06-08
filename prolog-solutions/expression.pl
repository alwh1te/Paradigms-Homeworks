lookup(K, [(K, V) | _], V).
lookup(K, [_ | T], V) :- lookup(K, T, V).


variable(Name, variable(Name)).
const(Value, const(Value)).

op_add(A, B, operation(op_add, A, B)).
op_subtract(A, B, operation(op_subtract, A, B)).
op_multiply(A, B, operation(op_multiply, A, B)).
op_divide(A, B, operation(op_divide, A, B)).
op_negate(A, operation(op_negate, A)).
op_sqrt(A, operation(op_sqrt, A)).
op_square(A, operation(op_square, A)).

operation(op_add, A, B, R) :- R is A + B.
operation(op_subtract, A, B, R) :- R is A - B.
operation(op_multiply, A, B, R) :- R is A * B.
operation(op_divide, A, B, R) :- R is A / B.
operation(op_negate, A, R) :- R is -A.
operation(op_sqrt, A, R) :- R is sqrt(A).
operation(op_square, A, R) :- R is (A * A).

evaluate(const(Value), _, Value).
evaluate(variable(Name), Vars, R) :- lookup(Name, Vars, R).

evaluate(operation(Op, A, B), Vars, R) :-
    evaluate(A, Vars, AV),
    evaluate(B, Vars, BV),
    operation(Op, AV, BV, R).

evaluate(operation(Op, A), Vars, R) :-
    evaluate(A, Vars, AV),
    operation(Op, AV, R).

nvar(V, _) :- var(V).
nvar(V, T) :- nonvar(V), call(T).


:- load_library('alice.tuprolog.lib.DCGLibrary').

expr_p(variable(Name)) --> [Name], { member(Name, [x, y, z]) }.
expr_p(const(Value)) -->
  { nvar(Value, number_chars(Value, Chars)) },
  digits_p(Chars),
  { Chars = [ _ | _ ], length(Chars, R), R > 1,Chars \= ['-'], number_chars(Value, Chars) }.

expr_p(operation(Op, A, B)) --> ['('], whitespace(W1), op_p(Op), [' '], expr_p(A), [' '], expr_p(B), whitespace(W2), [')'].
expr_p(operation(Op, A)) --> ['('], whitespace(W1), op_p(Op), [' '], expr_p(A), whitespace(W2), [')'].

expr_p_extended(E) --> whitespace(W1), expr_p(E), whitespace(W2).

digits_p([]) --> [].
digits_p([H | T]) -->
  { member(H, ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '-']) },
  [H],
  digits_p(T).

op_p(op_add) --> ['+'].
op_p(op_subtract) --> ['-'].
op_p(op_multiply) --> ['*'].
op_p(op_divide) --> ['/'].
op_p(op_negate) --> ['n', 'e', 'g', 'a', 't', 'e'].
op_p(op_sqrt) --> ['s', 'q', 'r', 't'].
op_p(op_square) --> ['s', 'q', 'u', 'a', 'r', 'e'].

whitespace(0) --> [].
whitespace(1) --> [' '].

skip_whitespaces([], []).
skip_whitespaces([' ', ' ' | T], [' ' | R]) :- skip_whitespaces([' ' | T], [' ' | R]).
skip_whitespaces([C | T], [C | R]) :- skip_whitespaces(T, R).

expr_str(E, A) :- ground(E), phrase(expr_p(E), C), atom_chars(A, C).
expr_str(E, A) :-   atom(A), atom_chars(A, C), phrase(expr_p(E), C).
prefix_str(E, A) :- ground(E), phrase(expr_p(E), C), atom_chars(A, C), !.
prefix_str(E, A) :- atom(A), atom_chars(A, C), skip_whitespaces(C, S), phrase(expr_p_extended(E), S), !.
example(operation(op_add,operation(op_multiply,variable(x),operation(op_subtract,variable(y),variable(z))),const(100))).


% ' (    /  5.0 z  )  '
% ' (  /  4.0     z)     '