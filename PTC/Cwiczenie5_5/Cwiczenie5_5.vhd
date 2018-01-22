library ieee;
use ieee.std_logic_1164.all;

entity Cwiczenie5_5 is
	Port(
		iSW: in std_logic_vector(7 downto 0);
		KEY: in std_logic_vector(0 to 0);
		HEX0, HEX1, HEX2, HEX3, HEX4, HEX5, HEX6, HEX7: out std_logic_vector(0 to 6)
	);
end Cwiczenie5_5;

architecture sumatory of Cwiczenie5_5 is
component mul4bit
	Port(
		a: in std_logic_vector(3 downto 0);
		b: in std_logic_vector(3 downto 0);
		w: out std_logic_vector(7 downto 0);
		clk: in std_logic
	);
end component;
component char7seg
	Port(
		number: in std_logic_vector(3 downto 0);
		display: out std_logic_vector(0 to 6)
	);
end component;
signal result: std_logic_vector(7 downto 0);
begin
	mul0: mul4bit port map(iSW(7 downto 4),iSW(3 downto 0),result,KEY(0));
	ohex0: char7seg port map(iSW(7 downto 4),HEX4);
	HEX6 <= "1111111";
	HEX5 <= "1111111";
	ohex3: char7seg port map(iSW(3 downto 0), HEX7);
	HEX3 <= "1111111";
	HEX2 <= "1111111";
	ohex6: char7seg port map(result(7 downto 4), HEX1);
	ohex7: char7seg port map(result(3 downto 0), HEX0);
end;