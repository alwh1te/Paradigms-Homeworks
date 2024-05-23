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


map_get(tree(Key, Value, _, _), Key, Value).

map_get(tree(RootKey, _, Left, _), Key, Value) :-
  Key < RootKey,
  map_get(Left, Key, Value).

map_get(tree(RootKey, _, _, Right), Key, Value) :-
  Key > RootKey,
  map_get(Right, Key, Value).


map_lastKey(tree(Key, Value, Left, nil), Key):- !.
map_lastKey(tree(Key, Value, Left, Right), K) :- map_lastKey(Right, K).

map_lastValue(tree(Key, Value, Left, nil), Value):- !.
map_lastValue(tree(Key, Value, Left, Right), V) :- map_lastValue(Right, V).

map_lastEntry(tree(Key, Value, Left, nil), (Key, Value)):- !.
map_lastEntry(tree(Key, Value, Left, Right), (K, V)):- map_lastEntry(Right, (K, V)).
