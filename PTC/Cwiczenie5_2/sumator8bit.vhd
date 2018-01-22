library ieee;
use ieee.std_logic_1164.all;

entity sumator8bit is
	Port(
		in1: in std_logic_vector(0 to 7);
		in2: in std_logic_vector(0 to 7);
		cin: in std_logic;
		sout: out std_logic_vector(0 to 7);
		cout: out std_logic
	);
end sumator8bit;

architecture sumatory of sumator8bit is
component sumator1bit
	Port(
		a,b,cin: in std_logic;
		s, cout: out std_logic
	);
end component;
signal C01, C12, C23, C34, C45, C56, C67: std_logic;
begin
	SUM0: sumator1bit port map(in1(0), in2(0), cin, sout(0), C01);
	SUM1: sumator1bit port map(in1(1), in2(1), C01, sout(1), C12);
	SUM2: sumator1bit port map(in1(2), in2(2), C12, sout(2), C23);
	SUM3: sumator1bit port map(in1(3), in2(3), C23, sout(3), C34);
	SUM4: sumator1bit port map(in1(4), in2(4), C34, sout(4), C45);
	SUM5: sumator1bit port map(in1(5), in2(5), C45, sout(5), C56);
	SUM6: sumator1bit port map(in1(6), in2(6), C56, sout(6), C67);
	SUM7: sumator1bit port map(in1(7), in2(7), C67, sout(7), cout);
end sumatory;
		