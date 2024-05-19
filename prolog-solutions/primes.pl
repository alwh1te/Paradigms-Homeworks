prime(N) :- \+ composite(N).

composite(N) :-
    N1 is round(sqrt(N)) + 1,
    smallest_divisor_in_range(2, N1, N, Q).

prime_divisors(1, []) :- !.
prime_divisors(N, [Divisor | Divisors]) :-
    smallest_prime_divisor(N, Divisor),
    N1 is N // Divisor,
    prime_divisors(N1, Divisors).

smallest_prime_divisor(N, R) :-
    SQRT is round(sqrt(N)) + 1,
    smallest_divisor_in_range(2, SQRT, N, R), !.
smallest_prime_divisor(N, N).

smallest_divisor_in_range(START, END, N, START) :-
    START < END,
    divided(N, START).

smallest_divisor_in_range(START, END, N, REST) :-
    START < END,
    S1 is START + 1,
    smallest_divisor_in_range(S1, END, N, REST).

divided(N, M) :- 0 =:= N mod M.

nth_prime(N, P) :-
    find_nth_prime(2, N, 1, P).

find_nth_prime(Current, N, Iterator, P) :-
    prime(Current),
    N =< Iterator,
    P is Current, !.

find_nth_prime(Current, N, Iterator, P) :-
    prime(Current),
    Iterator1 is Iterator + 1,
    Next is Current + 1,
    find_nth_prime(Next, N, Iterator1, P), !.

find_nth_prime(Current, N, Iterator, P) :-
    Next is Current + 1,
    find_nth_prime(Next, N, Iterator, P).
