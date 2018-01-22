library ieee;
use ieee.std_logic_1164.all;

entity sourceselectmux is
	Port(
		ain: in std_logic_vector(7 downto 0);
		zin: in std_logic_vector(7 downto 0);
		mout: out std_logic_vector(7 downto 0);
		source: in std_logic
	);
end sourceselectmux;

architecture sumatory of sourceselectmux is
begin
	mout(0) <= (ain(0) and not source) or (zin(0) and source);
	mout(1) <= (ain(1) and not source) or (zin(1) and source);
	mout(2) <= (ain(2) and not source) or (zin(2) and source);
	mout(3) <= (ain(3) and not source) or (zin(3) and source);
	mout(4) <= (ain(4) and not source) or (zin(4) and source);
	mout(5) <= (ain(5) and not source) or (zin(5) and source);
	mout(6) <= (ain(6) and not source) or (zin(6) and source);
	mout(7) <= (ain(7) and not source) or (zin(7) and source);

end sumatory;