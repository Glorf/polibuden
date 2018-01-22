library ieee;
use ieee.std_logic_1164.all;

entity mul4bitpart is
	Port(
		a: in std_logic_vector(3 downto 0);
		b: in std_logic;
		s: in std_logic_vector(3 downto 0);
		cin: in std_logic;
		w: out std_logic_vector(3 downto 0);
		cout: out std_logic
	);
end mul4bitpart;

architecture sumatory of mul4bitpart is
component sumator1bit
	Port(
		a,b,cin: in std_logic;
		s, cout: out std_logic
	);
end component;
signal C01, C12, C23: std_logic;
begin
	sum0: sumator1bit port map(s(1), a(0) and b, '0', w(0), C01);
	sum1: sumator1bit port map(s(2), a(1) and b, C01, w(1), C12);
	sum2: sumator1bit port map(s(3), a(2) and b, C12, w(2), C23);
	sum3: sumator1bit port map(cin, a(3) and b, C23, w(3), cout);
end sumatory;