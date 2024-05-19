map_build([], nil).

map_build([(Key, Value) | Tail], TreeMap) :-
  map_build(Tail, SubTree),
  map_insert(SubTree, Key, Value, TreeMap).


map_insert(nil, Key, Value, tree(Key, Value, nil, nil)).

map_insert(tree(Key, _, Left, Right), Key, Value, tree(Key, Value, Left, Right)).

map_insert(tree(RootKey, RootValue, Left, Right), Key, Value, NewTree) :-
  Key < RootKey,
  map_insert(Left, Key, Value, SubTree),
  NewTree = tree(RootKey, RootValue, SubTree, Right).

map_insert(tree(RootKey, RootValue, Left, Right), Key, Value, NewTree) :-
  Key > RootKey,
  map_insert(Right, Key, Value, SubTree),
  NewTree = tree(RootKey, RootValue, Left, SubTree).


map_get(tree(Key, Value, _, _), Key, Value).

map_get(tree(RootKey, _, Left, _), Key, Value) :-
  Key < RootKey,
  map_get(Left, Key, Value).

map_get(tree(RootKey, _, _, Right), Key, Value) :-
  Key > RootKey,
  map_get(Right, Key, Value).
