library ieee;
use ieee.std_logic_1164.all;

entity Cwiczenie5_4 is
	Port(
		iSW: in std_logic_vector(17 downto 0);
		KEY: in std_logic_vector(0 to 0);
		CLOCK_50: in std_logic;
		HEX0, HEX1, HEX2, HEX3, HEX4, HEX5, HEX6, HEX7: out std_logic_vector(0 to 6);
		oLEDR: out std_logic_vector(0 to 0)
	);
end Cwiczenie5_4;

architecture sumatory of Cwiczenie5_4 is
component sumtractor8bit
	Port(
		ain: in std_logic_vector(7 downto 0);
		bin: in std_logic_vector(7 downto 0);
		sel: in std_logic;
		clk: in std_logic;
		addsub: in std_logic;
		zout: out std_logic_vector(7 downto 0);
		overflow: out std_logic
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
	sumtract: sumtractor8bit port map(iSW(7 downto 0), iSW(15 downto 8), iSW(16), CLOCK_50, iSW(17), result ,oLEDR(0));
	-- wyświetl wejście1
	ohex0: char7seg port map(iSW(7 downto 4), HEX7);
	ohex1: char7seg port map(iSW(3 downto 0), HEX6);
	
	HEX5 <= "1111111";
	
	-- wyświetl wejście2
	ohex3: char7seg port map(iSW(15 downto 12), HEX4);
	ohex4: char7seg port map(iSW(11 downto 8), HEX3);
	
	HEX2 <= "1111111";
	
	-- wyświetl wyjście
	
	ohex6: char7seg port map(result(7 downto 4), HEX1);
	ohex7: char7seg port map(result(3 downto 0), HEX0);
	
end sumatory;
	