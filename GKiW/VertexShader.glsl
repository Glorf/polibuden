#version 330 core

//Zmienne v wchodzą do vertex shadera
//Zmienne f wychodzą do fragment shadera
//Zmienne u to zmienne jednorodne
//Zmienne MS są zorientowane w model space
//Zmienne CS są zorientowane w camera space
//Zmienne TS są zorientowane w tangent space

in vec3 vVertexMS;
in vec2 vTexCoords;
in vec3 vNormalsMS;
in vec3 vTangentsMS;
in vec3 vBitangentsMS;

out vec2 fTexCoords;
out vec3 fPositionWS;
out vec3 fEyeCS;
out vec3 fLightDirectionCS;
out vec3 fLightDirectionTS;
out vec3 fEyeTS;
out vec3 fNormalsMS;

uniform mat4 uP;
uniform mat4 uV;
uniform mat4 uM;
uniform vec3 uPosition;

void main(){

	gl_Position =  uP*uV*uM * vec4(vVertexMS,1);
	fPositionWS = (uM * vec4(vVertexMS,1)).xyz;
	
	mat4 MV = uV*uM;

	vec3 vertexCS = (MV * vec4(vVertexMS,1)).xyz;
	fEyeCS = normalize(vec3(0,0,0) - vertexCS);

	vec3 lightPositionCS = (uV*vec4(uPosition,1)).xyz;
	fLightDirectionCS = normalize(lightPositionCS + fEyeCS);
	
	fTexCoords = vTexCoords;
	fNormalsMS = vNormalsMS;

	mat3 MV3 = mat3(MV);

	vec3 vertexTangentCS = MV3 * vTangentsMS;
	vec3 vertexBitangentCS = MV3 * vBitangentsMS;
	vec3 vertexNormalCS = MV3 * vNormalsMS;
	
	mat3 TBN = transpose(mat3(
		vertexTangentCS,
		vertexBitangentCS,
		vertexNormalCS	
	));

	fLightDirectionTS = TBN * fLightDirectionCS;
	fEyeTS = TBN * fEyeCS;
}

