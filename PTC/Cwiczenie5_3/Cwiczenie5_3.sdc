## Generated SDC file "Cwiczenie5_3.sdc"

## Copyright (C) 1991-2013 Altera Corporation
## Your use of Altera Corporation's design tools, logic functions 
## and other software and tools, and its AMPP partner logic 
## functions, and any output files from any of the foregoing 
## (including device programming or simulation files), and any 
## associated documentation or information are expressly subject 
## to the terms and conditions of the Altera Program License 
## Subscription Agreement, Altera MegaCore Function License 
## Agreement, or other applicable license agreement, including, 
## without limitation, that your use is for the sole purpose of 
## programming logic devices manufactured by Altera and sold by 
## Altera or its authorized distributors.  Please refer to the 
## applicable agreement for further details.


## VENDOR  "Altera"
## PROGRAM "Quartus II"
## VERSION "Version 13.0.1 Build 232 06/12/2013 Service Pack 1 SJ Web Edition"

## DATE    "Tue Dec 19 13:59:38 2017"

##
## DEVICE  "EP2C70F896C6"
##


#**************************************************************
# Time Information
#**************************************************************

set_time_format -unit ns -decimal_places 3



#**************************************************************
# Create Clock
#**************************************************************

create_clock -name {zegar} -period 10.000 -waveform { 0.000 5.000 } [get_ports {clk}]


#**************************************************************
# Create Generated Clock
#**************************************************************



#**************************************************************
# Set Clock Latency
#**************************************************************



#**************************************************************
# Set Clock Uncertainty
#**************************************************************



#**************************************************************
# Set Input Delay
#**************************************************************

set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in1[0]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in1[1]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in1[2]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in1[3]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in1[4]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in1[5]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in1[6]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in1[7]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in2[0]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in2[1]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in2[2]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in2[3]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in2[4]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in2[5]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in2[6]}]
set_input_delay -add_delay  -clock [get_clocks {zegar}]  2.000 [get_ports {in2[7]}]


#**************************************************************
# Set Output Delay
#**************************************************************

set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {out0[0]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {out0[1]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {out0[2]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {out0[3]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {out0[4]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {out0[5]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {out0[6]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {out0[7]}]
set_output_delay -add_delay  -clock [get_clocks {zegar}]  1.000 [get_ports {overf}]


#**************************************************************
# Set Clock Groups
#**************************************************************



#**************************************************************
# Set False Path
#**************************************************************



#**************************************************************
# Set Multicycle Path
#**************************************************************



#**************************************************************
# Set Maximum Delay
#**************************************************************



#**************************************************************
# Set Minimum Delay
#**************************************************************



#**************************************************************
# Set Input Transition
#**************************************************************

