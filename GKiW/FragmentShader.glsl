#version 330 core

//Zmienne f wchodzą do fragment shadera
//Zmienne u to zmienne jednorodne
//Zmienna o to zmienna wyjściowa
//Zmienne MS są zorientowane w model space
//Zmienne CS są zorientowane w camera space
//Zmienne TS są zorientowane w tangent space

in vec2 fTexCoords;
in vec3 fPositionWS;
in vec3 fEyeCS;
in vec3 fLightDirectionCS;
in vec3 fLightDirectionTS;
in vec3 fEyeTS;
in vec3 fNormalsMS;

out vec3 oColor;

uniform sampler2D uTextureMap0;
uniform sampler2D uTextureMap1;
uniform sampler2D uTextureMap2;
uniform mat4 uM;
uniform mat4 uV;
uniform vec3 uPosition; //WorldSpace!
uniform float uLightPower;

void main(){

	vec3 LightColor = vec3(0.75,0.4,0.1);
	
	vec3 MaterialDiffuseColor = texture(uTextureMap0, fTexCoords).rgb; //zwykła tekstura
	vec3 MaterialAmbientColor = vec3(0.1,0.1,0.1) * MaterialDiffuseColor;
	vec3 MaterialSpecularColor = texture(uTextureMap2, fTexCoords).rgb; //tekstura spekular

	vec3 n = normalize(texture(uTextureMap1, fTexCoords).rgb*2.0-1.0);

	float distance = length(uPosition - fPositionWS);

	vec3 l = normalize(fLightDirectionCS);

	float cosTheta = clamp(dot(n, l), 0, 1);
	
	vec3 E = normalize(fEyeTS);
	vec3 R = reflect(-l, n);

	float cosAlpha = clamp(dot(E, R), 0, 1);


	//AMBIENT
    mat3 normalMatrix = transpose(inverse(mat3(uM)));
    vec3 normal = normalize(normalMatrix * fNormalsMS);
    vec3 lightPositionWS = vec3(6, 0.8, 0.75);
    
    vec3 sToL = lightPositionWS - fPositionWS; //vector from surface to light

    float brightness = dot(normal, sToL) / (length(sToL) * length(sToL) * length(normal) * length(normal));
    brightness = clamp(brightness, 0, 1);

    float brightnessAdjust = 1;
    vec3 surfaceColor = texture(uTextureMap0, fTexCoords).rgb;
    vec3 oColor2 = brightness * brightnessAdjust * surfaceColor.rgb * vec3(0, 0, 0.85);

	
	oColor =  MaterialAmbientColor +
		MaterialDiffuseColor * LightColor * uLightPower * cosTheta / (distance*distance) +
		MaterialSpecularColor * LightColor * uLightPower * pow(cosAlpha,5) / (distance*distance) + oColor2;

}
