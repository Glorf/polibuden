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