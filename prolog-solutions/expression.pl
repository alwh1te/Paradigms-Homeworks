lookup(K, [(K, V) | _], V).
lookup(K, [_ | T], V) :- lookup(K, T, V).


variable(Name, variable(Name)).
const(Value, const(Value)).

op_add(A, B, operation(op_add, A, B)).
op_subtract(A, B, operation(op_subtract, A, B)).
op_multiply(A, B, operation(op_multiply, A, B)).
op_divide(A, B, operation(op_divide, A, B)).
op_negate(A, operation(op_negate, A)).

operation(op_add, A, B, R) :- R is A + B.
operation(op_subtract, A, B, R) :- R is A - B.
operation(op_multiply, A, B, R) :- R is A * B.
operation(op_divide, A, B, R) :- R is A / B.
operation(op_negate, A, R) :- R is -A.

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
  { Chars = [_ | _], number_chars(Value, Chars) }.

expr_p(operation(Op, A, B)) --> ['('], op_p(Op), [' '], expr_p(A), [' '], expr_p(B), [')'].
expr_p(operation(Op, A)) --> ['('], op_p(Op), [' '], expr_p(A), [')'].

digits_p([]) --> [].
digits_p([H | T]) -->
  { member(H, ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.']) },
  [H],
  digits_p(T).

op_p(op_add) --> ['+'].
op_p(op_subtract) --> ['-'].
op_p(op_multiply) --> ['*'].
op_p(op_divide) --> ['/'].
op_p(op_negate) --> ['n', 'e', 'g', 'a', 't', 'e'].

skip_ws([], []).
skip_ws([X|Xs], [X|Ys]) :-
    non_whitespace(X),
    skip_ws_helper(Xs, Ys).

skip_ws([X|Xs], Ys) :-
    whitespace(X),
    skip_ws(Xs, Ys).

skip_ws_helper([], []).
%skip_ws_helper([' ' | Xs], Ys) :-
%    next_non_whitespace(Xs), !,
%    skip_ws_helper(Xs, Ys).

skip_ws_helper([' ' | Xs], Ys) :-
    skip_ws_helper(Xs, Ys).

skip_ws_helper([X|Xs], [X|Ys]) :-
    skip_ws_helper(Xs, Ys).

non_whitespace(X) :- X \= ' ', X \= '\t', X \= '\n'.
whitespace(' ').
%whitespace('\t').
whitespace('\n').

next_non_whitespace([]) :- false.
next_non_whitespace([' '|_]) :- false.
next_non_whitespace([X|_]) :- non_whitespace(X).

trim_whitespace(String, TrimmedString) :-
    atom_chars(String, Chars),
    remove_leading_spaces(Chars, TrimmedChars),
    remove_inner_spaces(TrimmedChars, InnerTrimmedChars),
    reverse(InnerTrimmedChars, ReversedChars),
    remove_leading_spaces(ReversedChars, ReversedTrimmedChars),
    remove_inner_spaces(ReversedTrimmedChars, ReversedInnerTrimmedChars),
    reverse(ReversedInnerTrimmedChars, ReversedInnerTrimmedChars2),
    atom_chars(TrimmedString, ReversedInnerTrimmedChars2).

remove_leading_spaces([], []).
remove_leading_spaces([' '|T], Trimmed) :-
    remove_leading_spaces(T, Trimmed).
remove_leading_spaces(Trimmed, Trimmed).

remove_inner_spaces([], []).
remove_inner_spaces([Space, Space|T], Trimmed) :-
    remove_inner_spaces([Space|T], Trimmed).
remove_inner_spaces([H|T], [H|Trimmed]) :-
    remove_inner_spaces(T, Trimmed).

% Преобразование в строку
expr_str(E, A) :- ground(E), phrase(expr_p(E), C), atom_chars(A, C).
expr_str(E, A) :-   atom(A), atom_chars(A, C), phrase(expr_p(E), C).
prefix_str(E, A) :- ground(E), phrase(expr_p(E), C), atom_chars(A, C).
%%%%prefix_str(E, A) :- atom(A), atom_chars(A, C), phrase(expr_p(E), S), !.
prefix_str(E, A) :- atom(A), atom_chars(A, C), skip_ws(C, S), phrase(expr_p(E), S), !.
prefix_str(E, A) :- atom(A), atom_chars(A, C), phrase(expr_p(E), C).
example(operation(op_add,operation(op_multiply,variable(x),operation(op_subtract,variable(y),variable(z))),const(100))).


% ' (    /  5.0 z  )  '
% ' (  /  4.0     z)     '