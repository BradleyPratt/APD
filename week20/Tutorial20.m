ZERO = [1;0];
ONE = [0;1];

Hadamard = (1/sqrt(2))*[1,1;1,-1];

Identity = [1,0;0,1];
PauliX = [0,1;1,0];
PauliY = [0,-1i;1i 0];
PauliZ = [1,0;0,-1];

Xaxis = [1;0;0];
Yaxis = [0;1;0];
Zaxis = [0;0;1];

sqrtNOT = (1/sqrt(2))*[1,-1;1,1];

cNOT = [1,0,0,0;0,1,0,0;0,0,0,1;0,0,1,0];

rot = @(vector,theta) cos(theta)*Identity - 1i*sin(theta)*(vector(1)*PauliX + vector(2)*PauliY + vector(3)*PauliZ);

Uf = @(n,m) [~n,n,0,0;n,~n,0,0;0,0,~m,m;0,0,m,~m];

Deutsch = @(n,m) kron(Hadamard,Identity)*Uf(n,m)*kron(Hadamard,Hadamard);

%Hadamard * ZERO = 0.7071
%Hadamard * 0.7071 = 1 or 0

%The results with ONE are the same