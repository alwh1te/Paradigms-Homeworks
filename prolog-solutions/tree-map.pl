abs(X, X) :- X >= 0.
abs(X, Y) :- X < 0, Y is -X.

length([], 0).
length([_|Tail], N) :-
  length(Tail, M),
  N is M + 1.


map_build([], nil).

map_build(List, TreeMap) :-
  length(List, Length),
  HalfLength is Length // 2,
  split_at(HalfLength, List, Left, [(Key, Value) | Right]),
  map_build(Left, LeftSubTree),
  map_build(Right, RightSubTree),
  TreeMap = tree(Key, Value, LeftSubTree, RightSubTree).


split_at(N, List, [], List) :-
  N =< 0.

split_at(N, [X|Xs], [X|Ys], Zs) :-
  N > 0,
  M is N - 1,
  split_at(M, Xs, Ys, Zs).


map_insert(nil, Key, Value, tree(Key, Value, nil, nil)).

map_insert(tree(Key, Value, Left, Right), Key, Value, tree(Key, Value, Left, Right)).

map_insert(tree(RootKey, RootValue, Left, Right), Key, Value, NewTree) :-
  Key < RootKey,
  map_insert(Left, Key, Value, SubTree),
  update_height(tree(RootKey, RootValue, SubTree, Right), NewTree).

map_insert(tree(RootKey, RootValue, Left, Right), Key, Value, NewTree) :-
  Key > RootKey,
  map_insert(Right, Key, Value, SubTree),
  update_height(tree(RootKey, RootValue, Left, SubTree), NewTree).


update_height(tree(Key, Value, Left, Right), tree(Key, Value, Left, Right)) :-
  height(Left, LH),
  height(Right, RH),
  Diff is LH - RH,
  abs(Diff) =< 1.

update_height(tree(Key, Value, Left, Right), NewTree) :-
  height(Left, LH),
  height(Right, RH),
  Diff is LH - RH,
  abs(Diff) > 1,
  rotate(tree(Key, Value, Left, Right), NewTree).

rotate(tree(K1, V1, tree(K2, V2, L2, R2), R1), tree(K2, V2, L2, tree(K1, V1, R2, R1))) :-
  height(L2, H1),
  height(R2, H2),
  H1 >= H2.

rotate(tree(K1, V1, L1, tree(K2, V2, L2, R2)), tree(K2, V2, tree(K1, V1, L1, L2), R2)).

height(nil, 0).

height(tree(_, _, Left, Right), H) :-
  height(Left, LH),
  height(Right, RH),
  H is max(LH, RH) + 1.


map_get(tree(Key, Value, _, _), Key, Value).

map_get(tree(RootKey, _, Left, _), Key, Value) :-
  Key < RootKey,
  map_get(Left, Key, Value).

map_get(tree(RootKey, _, _, Right), Key, Value) :-
  Key > RootKey,
  map_get(Right, Key, Value).
