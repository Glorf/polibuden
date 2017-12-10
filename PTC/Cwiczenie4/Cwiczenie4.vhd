library ieee;
use ieee.std_logic_1164.all;

entity Cwiczenie4 is
	Port(
		iSW	: in std_logic_vector(17 downto 0);
		HEX0, HEX1, HEX2, HEX3, HEX4, HEX5, HEX6	: out std_logic_vector(0 to 6));
end Cwiczenie4;

architecture strukturalna of Cwiczenie4 is

constant Spacja: std_logic_vector(2 downto 0):="000";

component mux3bit8to1
	port(
		S, U0, U1, U2, U3, U4, U5, U6: in std_logic_vector(2 downto 0);
		M :  out std_logic_vector(2 downto 0));
end component;
	
component char7seg
	port(
		Code : in std_logic_vector(2 downto 0);
		Display: out std_logic_vector(0 to 6));
end component;

signal M0, M1, M2, M3, M4, M5, M6: std_logic_vector(2 downto 0);

begin
	Mux0: mux3bit8to1 port map (iSW(17 downto 15), iSW(14 downto 12), iSW(11 downto 9),
		iSW(8 downto 6), iSW(5 downto 3), iSW(2 downto 0), Spacja, Spacja, M0);
		
	Mux1: mux3bit8to1 port map (iSW(17 downto 15), iSW(11 downto 9), iSW(8 downto 6), 
		iSW(5 downto 3), iSW(2 downto 0), Spacja, Spacja, iSW(14 downto 12), M1);
		
	Mux2: mux3bit8to1 port map (iSW(17 downto 15), iSW(8 downto 6), iSW(5 downto 3), 
		iSW(2 downto 0), Spacja, Spacja, iSW(14 downto 12),iSW(11 downto 9), M2);
		
	Mux3: mux3bit8to1 port map (iSW(17 downto 15), iSW(5 downto 3), iSW(2 downto 0),
		Spacja, Spacja, iSW(14 downto 12),iSW(11 downto 9), iSW(8 downto 6), M3);
		
	Mux4: mux3bit8to1 port map (iSW(17 downto 15), iSW(2 downto 0), Spacja, Spacja,
		iSW(14 downto 12), iSW(11 downto 9), iSW(8 downto 6), iSW(5 downto 3), M4);
		
	Mux5: mux3bit8to1 port map (iSW(17 downto 15), Spacja, Spacja, iSW(14 downto 12),
		iSW(11 downto 9), iSW(8 downto 6), iSW(5 downto 3), iSW(2 downto 0), M5);
		
	Mux6: mux3bit8to1 port map (iSW(17 downto 15), Spacja, iSW(14 downto 12), 
		iSW(11 downto 9), iSW(8 downto 6), iSW(5 downto 3), iSW(2 downto 0), Spacja, M6);
		
	H0: char7seg port map(M0, HEX0);
	H1: char7seg port map(M1, HEX1);
	H2: char7seg port map(M2, HEX2);
	H3: char7seg port map(M3, HEX3);
	H4: char7seg port map(M4, HEX4);
	H5: char7seg port map(M5, HEX5);
	H6: char7seg port map(M6, HEX6);
	
end strukturalna;

library ieee;
use ieee.std_logic_1164.all;

entity mux3bit8to1 is
	port (
		S,U0,U1,U2,U3,U4,U5,U6: in std_logic_vector(2 downto 0);
		M: out std_logic_vector(2 downto 0));
end mux3bit8to1;


architecture strukturalna of mux3bit8to1 is
begin
		M(2) <=  (U0(2) and not S(2) and not S(1) and not S(0)) or
				(U1(2) and not S(2) and not S(1) and S(0)) or
				(U2(2) and not S(2) and S(1) and not S(0)) or
				(U3(2) and not S(2) and S(1) and S(0)) or
				(U4(2) and S(2) and not S(1) and not S(0)) or
				(U5(2) and S(2) and not S(1) and S(0)) or
				(U6(2) and S(2) and S(1));

		M(1) <=  (U0(1) and not S(2) and not S(1) and not S(0)) or
				(U1(1) and not S(2) and not S(1) and S(0)) or
				(U2(1) and not S(2) and S(1) and not S(0)) or
				(U3(1) and not S(2) and S(1) and S(0)) or
				(U4(1) and S(2) and not S(1) and not S(0)) or
				(U5(1) and S(2) and not S(1) and S(0)) or
				(U6(1) and S(2) and S(1));
		
		M(0) <=  (U0(0) and not S(2) and not S(1) and not S(0)) or
				(U1(0) and not S(2) and not S(1) and S(0)) or
				(U2(0) and not S(2) and S(1) and not S(0)) or
				(U3(0) and not S(2) and S(1) and S(0)) or
				(U4(0) and S(2) and not S(1) and not S(0)) or
				(U5(0) and S(2) and not S(1) and S(0)) or
				(U6(0) and S(2) and S(1));
end strukturalna;

library ieee;
use ieee.std_logic_1164.all;

entity char7seg is
	port(
		Code: in std_logic_vector(2 downto 0);
		Display: out std_logic_vector(0 to 6));
end char7seg;

architecture strukturalna of char7seg is

constant Spacja: std_logic_vector(2 downto 0):="000";
constant LiteraB: std_logic_vector(2 downto 0):="001";
constant LiteraC: std_logic_vector(2 downto 0):="010";
constant LiteraE: std_logic_vector(2 downto 0):="011";
constant LiteraF: std_logic_vector(2 downto 0):="100";
constant LiteraG: std_logic_vector(2 downto 0):="101";

begin
	with Code select
		Display <=  "1111111" when Spacja,
						"1100000" when LiteraB,
						"0110001" when LiteraC,
						"0110000" when LiteraE,
						"0111000" when LiteraF,
						"0100000" when LiteraG,
						"1111111" when others;
end strukturalna;