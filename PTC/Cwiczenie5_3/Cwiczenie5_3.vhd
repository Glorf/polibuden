library ieee;
use ieee.std_logic_1164.all;

entity Cwiczenie5_3 is
	Port(
		in1: in std_logic_vector(0 to 7);
		in2: in std_logic_vector(0 to 7);
		clk: in std_logic;
		out0: out std_logic_vector(0 to 7);
		overf: out std_logic
	);
end Cwiczenie5_3;

architecture sumatory of Cwiczenie5_3 is
component sumator8bitseq
	Port(
		in1: in std_logic_vector(0 to 7);
		in2: in std_logic_vector(0 to 7);
		clk: in std_logic;
		out0: out std_logic_vector(0 to 7);
		overf: out std_logic
	);
end component;
begin
	sum: sumator8bitseq port map(in1, in2, clk, out0, overf);
end sumatory;