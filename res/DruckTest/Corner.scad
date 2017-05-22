union(){
	union(){
	rotate(45.00,[0,0,1]){
	translate([11.00, 0.00, 1.50]){
	union(){
	translate([0.00, 0.00, 0.00]){
	cube([22.00,4.00,2.00], true);
}	translate([15.00, 0.00, 0.00]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 2.000, r = 4.000, center = true, $fa = 5, $fs = 0.01);
}}	translate([11.00, 0.00, 0.00]){
	translate([0.00, 0.00, 0.00]){
	cube([1.00,4.00,2.00], true);
}}}}}	intersection(){
	translate([0.00, 0.00, 0.50]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.000, r = 34.000, center = true, $fa = 5, $fs = 0.01);
}}	translate([-0.00, -0.00, 0.00]){
	scale([1.000, 1.000, 8.000]){
	linear_extrude(1.00, center = true){
polygon([[0.0,0.0], [100.0,0.0], [100.0,100.0], [0.0,100.0]],10);
}}}}	translate([0.00, 0.00, 2.00]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 4.000, r = 10.000, center = true, $fa = 5, $fs = 0.01);
}}}	union(){
	difference(){
	translate([0.00, 0.00, 0.50]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.000, r = 24.000, center = true, $fa = 5, $fs = 0.01);
}}	translate([-0.00, -0.00, 0.00]){
	scale([1.000, 1.000, 8.000]){
	linear_extrude(1.00, center = true){
polygon([[100.0,0.0], [0.0,0.0], [0.0,100.0], [100.0,100.0]],10);
}}}}	translate([0.00, 0.00, 2.00]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 4.000, r = 10.000, center = true, $fa = 5, $fs = 0.01);
}}}	translate([0.00, 0.00, 9.50]){
	scale([1.000, 1.000, 11.000]){
	difference(){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.000, r = 10.000, center = true, $fa = 5, $fs = 0.01);
}	rotate(0.00,[0,0,1]){
	translate([10.00, 0.00, 0.00]){
	cube([10.00,3.00,4.00], true);
}}	rotate(90.00,[0,0,1]){
	translate([10.00, 0.00, 0.00]){
	cube([10.00,3.00,4.00], true);
}}}}}}

