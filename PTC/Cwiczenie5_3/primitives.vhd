library ieee;
use ieee.std_logic_1164.all;
library altera;
use altera.altera_primitives_components.all;

entity mydff is
	Port(
		d, clk : in std_logic;
		q : inout std_logic
	);
end mydff;

architecture sumatory of mydff is
component dff
Port(
	d: in std_logic;
	clk: in std_logic;
	clrn: in std_logic;
	prn: in std_logic;
	q : out std_logic
	);
end component;
begin
	shortdff: dff port map(d, clk, '1', '1', q);
end sumatory;

library ieee;
use ieee.std_logic_1164.all;

entity rejestr8bit is
	Port(
		clk: in std_logic;
		r: in std_logic_vector(0 to 7);
		q: out std_logic_vector(0 to 7)
	);
end rejestr8bit;
architecture sumatory of rejestr8bit is
component mydff
	Port(
		d, clk : in std_logic;
		q : out std_logic
	);
end component;
begin
	dff0: mydff port map(r(0), clk, q(0));
	dff1: mydff port map(r(1), clk, q(1));
	dff2: mydff port map(r(2), clk, q(2));
	dff3: mydff port map(r(3), clk, q(3));
	dff4: mydff port map(r(4), clk, q(4));
	dff5: mydff port map(r(5), clk, q(5));
	dff6: mydff port map(r(6), clk, q(6));
	dff7: mydff port map(r(7), clk, q(7));
end sumatory;

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
