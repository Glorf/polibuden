library ieee;
use ieee.std_logic_1164.all;

entity sumator1bit is
	Port(
		a,b,cin: in std_logic;
		s, cout: out std_logic
	);
end sumator1bit;

architecture sumatory of sumator1bit is
begin
	cout <= (b and a) or (cin and a) or (cin and b);
	s <= (a xor b) xor cin;
end sumatory;