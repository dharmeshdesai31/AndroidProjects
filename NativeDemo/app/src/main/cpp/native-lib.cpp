#include <jni.h>
#include <string>
#include <stdlib.h>

#include <android/native_window.h>  // For MS Windows
#include <GLES2/gl2.h>
#include <GLES3/gl3.h>
#include <stdio.h>
#include <stdlib.h>
#include <cstdlib>
#include <iostream>
#include <vector>
#include <GLES3/gl3.h>
#include <string>

using namespace std;


GLuint LoadShader(){

    GLuint vertShader = glCreateShader(GL_VERTEX_SHADER);
    GLuint fragShader = glCreateShader(GL_FRAGMENT_SHADER);

    //read
    string vShaderStr =
            "attribute vec4 vPosition; \n"
                    "void main() \n"
                    "{ \n"
                    " gl_Position = vPosition; \n"
                    "} \n";
    string fShaderStr =
            "precision mediump float; \n"
                    "void main() \n"
                    "{ \n"
                    " gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0); \n"
                    "} \n";

    const char *vertShaderSrc = vShaderStr.c_str();
    const char *fragShaderSrc = fShaderStr.c_str();

    GLint result = GL_FALSE;
    int logLength;

    //compile
    cout<<"Compiling vertex shader"<<endl;
    glShaderSource(vertShader, 1, &vertShaderSrc, NULL);
    glCompileShader(vertShader);

    //check vertex shader
    glGetShaderiv(vertShader, GL_COMPILE_STATUS, &result);
    glGetShaderiv(fragShader, GL_INFO_LOG_LENGTH, &logLength);
    vector<GLchar> vertShaderError((logLength > 1) ? logLength : 1);
    glGetShaderInfoLog(vertShader, logLength, NULL, &vertShaderError[0]);
    cout<<&vertShaderError[0]<<endl;

    //compile fragShader
    cout<<"compiling frag shader"<<endl;
    glShaderSource(fragShader, 1, &fragShaderSrc, NULL);
    glCompileShader(fragShader);

    //check frag shader
    glGetShaderiv(fragShader, GL_COMPILE_STATUS, &result);
    glGetShaderiv(fragShader, GL_INFO_LOG_LENGTH, &logLength);
    vector<GLchar> fragShaderError((logLength > 1) ? logLength : 1);
    glGetShaderInfoLog(fragShader, logLength, NULL, &fragShaderError[0]);
    cout<<&fragShaderError[0]<<endl;

    //linking program
    GLuint program = glCreateProgram();
    glAttachShader(program, vertShader);
    glAttachShader(program, fragShader);
    glLinkProgram(program);

    glGetProgramiv(program, GL_LINK_STATUS, &result);
    glGetProgramiv(program, GL_INFO_LOG_LENGTH, &logLength);
    vector<char> programError((logLength > 1) ? logLength : 1);
    glGetShaderInfoLog(program, logLength, NULL, &programError[0]);
    cout<<&programError[0]<<endl;

    glDeleteShader(vertShader);
    glDeleteShader(fragShader);

    return program;

}

extern "C"
JNIEXPORT jstring JNICALL
Java_demo_test_com_nativedemo_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";

    /*GLfloat vVertices[] = {  0.0f,  0.5f, 0.0f,
                             -0.5f, -0.5f, 0.0f,
                             0.5f, -0.5f, 0.0f
    };*/

    vector<float> geometryData;
    geometryData.push_back(-0.5); geometryData.push_back(-0.5);
    geometryData.push_back(0.0); geometryData.push_back(1.0);

    geometryData.push_back(0.5); geometryData.push_back(-0.5);
    geometryData.push_back(0.0); geometryData.push_back(1.0);

    geometryData.push_back(0.0); geometryData.push_back(0.5);
    geometryData.push_back(0.0); geometryData.push_back(1.0);

    GLuint  m_geometryBuffer;
    glGenBuffers(1, &m_geometryBuffer);
    glBindBuffer(GL_ARRAY_BUFFER, m_geometryBuffer);

    glBufferData(GL_ARRAY_BUFFER, geometryData.size() * sizeof(float), &geometryData[0], GL_STATIC_DRAW);

    //adding color
    vector<float> colorData;

    colorData.push_back(1.0); colorData.push_back(0.0); colorData.push_back(0.0);
    colorData.push_back(0.0); colorData.push_back(1.0); colorData.push_back(0.0);
    colorData.push_back(0.0); colorData.push_back(0.0); colorData.push_back(1.0);

    GLuint m_colorBuffer;
    glGenBuffers(1, &m_colorBuffer);
    glBindBuffer(GL_ARRAY_BUFFER, m_colorBuffer);

    glBufferData(GL_ARRAY_BUFFER, colorData.size() * sizeof(float), &colorData[0], GL_STATIC_DRAW);

    GLuint program = LoadShader();
    glUseProgram(program);

    GLuint m_positionLocation = glGetAttribLocation(program, "position");
    GLuint  m_colorLocation = glGetAttribLocation(program, "color");

    if(m_positionLocation < 0 || m_colorLocation < 0){
        cout<<"could not query attrib location"<<endl;
    }

    glEnableVertexAttribArray(m_positionLocation);
    glEnableVertexAttribArray(m_colorLocation);

    glBindBuffer(GL_ARRAY_BUFFER, m_geometryBuffer);
    glVertexAttribPointer(m_positionLocation, 4, GL_FLOAT, GL_FALSE, 0, NULL);

    glBindBuffer(GL_ARRAY_BUFFER, m_colorBuffer);
    glVertexAttribPointer(m_colorBuffer, 3, GL_FLOAT, GL_FALSE, 0, NULL);

    glDrawArrays(GL_TRIANGLES, 0, 3);

    return env->NewStringUTF(hello.c_str());
}