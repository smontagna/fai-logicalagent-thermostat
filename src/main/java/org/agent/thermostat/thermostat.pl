tolerance(0.5).

next_action(heating) :-
    target_temperature(Target),
    current_temperature(Current),
	tolerance(Delta),
	T1 is Target - Delta,
    Current < T1.

next_action(cooling) :-
    target_temperature(Target),
    current_temperature(Current),
	tolerance(Delta),
	T1 is Target + Delta,
    Current > T1.