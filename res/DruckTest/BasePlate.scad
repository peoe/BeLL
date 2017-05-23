difference(){
	translate([0.00, 0.00, 2.00]){
	scale([1.000, 1.000, 4.000]){
	linear_extrude(1.00, center = true){
offset(delta = -0.125){polygon([[-3.25,-3.25], [103.25,-3.25], [103.25,103.25], [-3.25,103.25]],10);
}}}}	translate([0.00, 0.00, 0.00]){
	union(){
	rotate(45.00,[0,0,1]){
	translate([11.00, 0.00, 1.50]){
	union(){
	translate([0.00, 0.00, 0.13]){
	cube([22.00,4.50,2.25], true);
}	translate([15.00, 0.00, 0.00]){
	translate([0.00, 0.00, 0.13]){
	cylinder(h = 2.250, r = 4.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([11.00, 0.00, 0.00]){
	translate([0.00, 0.00, 0.13]){
	cube([1.00,4.50,2.25], true);
}}}}}	intersection(){
	translate([0.00, 0.00, 0.50]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.500, r = 34.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([-0.00, -0.00, 0.00]){
	scale([1.000, 1.000, 8.000]){
	linear_extrude(1.00, center = true){
offset(delta = 0.250){polygon([[0.0,0.0], [100.0,0.0], [100.0,100.0], [0.0,100.0]],10);
}}}}}	translate([0.00, 0.00, 0.50]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.500, r = 24.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([0.00, 0.00, 2.00]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 4.000, r = 10.250, center = true, $fa = 5, $fs = 0.01);
}}}}	translate([100.00, 0.00, 0.00]){
	union(){
	rotate(135.00,[0,0,1]){
	translate([11.00, 0.00, 1.50]){
	union(){
	translate([0.00, 0.00, 0.13]){
	cube([22.00,4.50,2.25], true);
}	translate([15.00, 0.00, 0.00]){
	translate([0.00, 0.00, 0.13]){
	cylinder(h = 2.250, r = 4.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([11.00, 0.00, 0.00]){
	translate([0.00, 0.00, 0.13]){
	cube([1.00,4.50,2.25], true);
}}}}}	intersection(){
	translate([0.00, 0.00, 0.50]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.500, r = 34.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([-100.00, -0.00, 0.00]){
	scale([1.000, 1.000, 8.000]){
	linear_extrude(1.00, center = true){
offset(delta = 0.250){polygon([[0.0,0.0], [100.0,0.0], [100.0,100.0], [0.0,100.0]],10);
}}}}}	translate([0.00, 0.00, 0.50]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.500, r = 24.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([0.00, 0.00, 2.00]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 4.000, r = 10.250, center = true, $fa = 5, $fs = 0.01);
}}}}	translate([100.00, 100.00, 0.00]){
	union(){
	rotate(225.00,[0,0,1]){
	translate([11.00, 0.00, 1.50]){
	union(){
	translate([0.00, 0.00, 0.13]){
	cube([22.00,4.50,2.25], true);
}	translate([15.00, 0.00, 0.00]){
	translate([0.00, 0.00, 0.13]){
	cylinder(h = 2.250, r = 4.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([11.00, 0.00, 0.00]){
	translate([0.00, 0.00, 0.13]){
	cube([1.00,4.50,2.25], true);
}}}}}	intersection(){
	translate([0.00, 0.00, 0.50]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.500, r = 34.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([-100.00, -100.00, 0.00]){
	scale([1.000, 1.000, 8.000]){
	linear_extrude(1.00, center = true){
offset(delta = 0.250){polygon([[0.0,0.0], [100.0,0.0], [100.0,100.0], [0.0,100.0]],10);
}}}}}	translate([0.00, 0.00, 0.50]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.500, r = 24.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([0.00, 0.00, 2.00]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 4.000, r = 10.250, center = true, $fa = 5, $fs = 0.01);
}}}}	translate([0.00, 100.00, 0.00]){
	union(){
	rotate(-45.00,[0,0,1]){
	translate([11.00, 0.00, 1.50]){
	union(){
	translate([0.00, 0.00, 0.13]){
	cube([22.00,4.50,2.25], true);
}	translate([15.00, 0.00, 0.00]){
	translate([0.00, 0.00, 0.13]){
	cylinder(h = 2.250, r = 4.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([11.00, 0.00, 0.00]){
	translate([0.00, 0.00, 0.13]){
	cube([1.00,4.50,2.25], true);
}}}}}	intersection(){
	translate([0.00, 0.00, 0.50]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.500, r = 34.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([-0.00, -100.00, 0.00]){
	scale([1.000, 1.000, 8.000]){
	linear_extrude(1.00, center = true){
offset(delta = 0.250){polygon([[0.0,0.0], [100.0,0.0], [100.0,100.0], [0.0,100.0]],10);
}}}}}	translate([0.00, 0.00, 0.50]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 1.500, r = 24.250, center = true, $fa = 5, $fs = 0.01);
}}	translate([0.00, 0.00, 2.00]){
	translate([0.00, 0.00, 0.00]){
	cylinder(h = 4.000, r = 10.250, center = true, $fa = 5, $fs = 0.01);
}}}}}