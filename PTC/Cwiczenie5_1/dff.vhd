library ieee;
use ieee.std_logic_1164.all;

entity mydff is
	Port(
		d, clk : in std_logic;
		q : inout std_logic
	);
end mydff;

architecture sumatory of mydff is
signal d1,s1,r1,qb: std_logic;
begin
	s1 <= d nand clk;
	d1 <= d nand d;
	r1 <= d1 nand clk;
	qb <= r1 nand q;
	q <= s1 nand qb;
end sumatory;