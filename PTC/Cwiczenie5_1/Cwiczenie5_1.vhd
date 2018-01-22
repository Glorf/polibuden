library ieee;
use ieee.std_logic_1164.all;

entity Cwiczenie5_1 is
	Port(
		sumator_in: in std_logic_vector(0 to 2);
		sumator_out: out std_logic_vector(0 to 1);
		dff_in: in std_logic_vector(0 to 1);
		dff_out: out std_logic
	);
end Cwiczenie5_1;

architecture sumatory of Cwiczenie5_1 is
component sumator1bit
	Port(
		a,b,cin: in std_logic;
		s,cout: out std_logic
	);
end component;
component mydff
	Port(
		d, clk : in std_logic;
		q : out std_logic
	);
end component;
begin
	Sum0: sumator1bit port map(sumator_in(0), sumator_in(1),sumator_in(2),sumator_out(0), sumator_out(1));
	Reg0: mydff port map(dff_in(0), dff_in(1), dff_out);
end sumatory;