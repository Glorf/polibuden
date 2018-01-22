library ieee;
use ieee.std_logic_1164.all;

entity sumator8bitseq is
	Port(
		in1: in std_logic_vector(0 to 7);
		in2: in std_logic_vector(0 to 7);
		clk: in std_logic;
		out0: out std_logic_vector(0 to 7);
		overf: out std_logic
	);
end sumator8bitseq;

architecture sumatory of sumator8bitseq is
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
component mydff
	Port(
		d, clk : in std_logic;
		q : out std_logic
	);
end component;
signal signal1in, signal2in, signalout: std_logic_vector(0 to 7);
signal overflowsig: std_logic;
begin
	rejestr1in: rejestr8bit port map(clk, in1, signal1in);
	rejestr2in: rejestr8bit port map(clk, in2, signal2in);
	sumator: sumator8bit port map(signal1in, signal2in, '0', signalout, overflowsig);
	rejestrout: rejestr8bit port map(clk, signalout, out0);
	overflowout: mydff port map(overflowsig, clk, overf);
end sumatory;