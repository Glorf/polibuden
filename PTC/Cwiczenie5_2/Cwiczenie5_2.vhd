library ieee;
use ieee.std_logic_1164.all;

entity Cwiczenie5_2 is
	Port(
		a: in std_logic_vector(0 to 7);
		b: in std_logic_vector(0 to 7);
		cin: in std_logic;
		clk: in std_logic;
		sum: out std_logic_vector(0 to 7);
		sout: out std_logic
	);
end Cwiczenie5_2;

architecture sumatory of Cwiczenie5_2 is
component sumator8bit
	Port(
		in1: in std_logic_vector(0 to 7);
		in2: in std_logic_vector(0 to 7);
		cin: in std_logic;
		sout: out std_logic_vector(0 to 7);
		cout: out std_logic
	);
end component;
component rejestr8bit
	Port(
		clk: in std_logic;
		r: in std_logic_vector(0 to 7);
		q: out std_logic_vector(0 to 7)
	);
end component;
signal s1, s2: std_logic_vector(0 to 7);
begin
	R0: rejestr8bit port map(clk, a, s1);
	R1: rejestr8bit port map(clk, b, s2);
	SUMUJ: sumator8bit port map(s1, s2, cin, sum, sout);
	
end sumatory;
