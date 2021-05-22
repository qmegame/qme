package org.qme.client.vis.gl;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL33.*;

/**
 * Yeah, Cameron, I know you're going to be reading this frantically trying to
 * figure out how shaders work. Or someone else, maybe. So I'm writing it here
 * the way I understand.
 *
 * OpenGL can compile GLSL code into GPU-assembly language. You can then bind
 * the shader so sets of vertices are rendered with it. OpenGL renders shaders
 * by compiling a String of source code, which we generate on-the-fly from a
 * file. The vertex shader (_vert.glsl) is first, which produces the position of
 * the vertex being processed. I *think* the fragment shader makes the output
 * color? Or something? Idk. The "real" rendering work is done by meshes.
 *
 * @author adamhutchings
 * @since 0.4
 */
public class Shader {

    private final int programId, vertId, fragId;

    private final Map<String, Integer> uniforms = new HashMap<>();

    /**
     * Load a string from a file.
     * @param fileName the path to the file
     */
    public static String loadFileAsString(String fileName) throws Exception {
        return Files.readString(Path.of(fileName));
    }

    /**
     * Load a shader from files.
     * @param fileBase load vertex code from src/shader/fileBase_vert.glsl
     * and src/shader/fileBase_frag.glsl
     */
    public Shader(String fileBase) throws Exception {

        programId = glCreateProgram();
        if (programId == 0) {
            throw new Exception("Unable to create parent shader");
        }

        vertId = createShader(
                loadFileAsString("src/shader/" + fileBase + "_vert.glsl"), GL_VERTEX_SHADER
        );
        fragId = createShader(
                loadFileAsString("src/shader/" + fileBase + "_frag.glsl"), GL_FRAGMENT_SHADER
        );

        link();

    }

    /**
     * Link everything together.
     */
    private void link() throws Exception {

        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking shader code: " + glGetProgramInfoLog(programId, 1024));
        }

        // These pieces of shaders aren't "needed" anymore, so we can detach
        // them from the final linked program.
        if (vertId != 0) {
            glDetachShader(programId, vertId);
        }
        if (fragId != 0) {
            glDetachShader(programId, fragId);
        }

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            // THESE WARNINGS ARE NOT NECESSARILY A SIGN OF A FAILING COMPILATION.
            // Just so y'all know :D
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024));
        }

    }

    /**
     * Create an individual shader of a given type.
     */
    private int createShader(String code, int type) throws Exception {

        int shaderId = glCreateShader(type);

        if (shaderId == 0) {
            throw new Exception("Could not create shader of type " + type);
        }

        glShaderSource(shaderId, code);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(programId, shaderId);

        return shaderId;

    }

    /**
     * Set this as the shader to be used.
     */
    public void bind() {
        glUseProgram(programId);
    }

    /**
     * Stop using this shader.
     */
    public void unbind() {
        glUseProgram(0);
    }

    /**
     * Call this to delete the shader.
     */
    public void cleanup() {
        unbind();
        // This check should technically be unnecessary.
        if (programId != 0) {
            glDeleteProgram(programId);
        }
    }

    /**
     * For us to pass data into the shader from the program, we need to create a
     * "uniform", which like all other OpenGL code is set using integers. Here
     * we interface with it using strings so that we can access it more intuitively
     * and easily.
     * @param uniformName the name of the new uniform to create
     * @throws Exception if the uniform is not defined *in the shader program*.
     */
    public void createUniform(String uniformName) throws Exception {
        int uniformLocation = glGetUniformLocation(programId,
                uniformName);
        if (uniformLocation < 0) {
            throw new Exception("Could not find uniform:" +
                    uniformName);
        }
        uniforms.put(uniformName, uniformLocation);
    }

    /**
     * Set a uniform to an int value
     * @param uniformName which uniform
     * @param value the value to set it to
     */
    public void setUniform(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }

    /**
     * Set a uniform to an float value
     * @param uniformName which uniform
     * @param value the value to set it to
     */
    public void setUniform(String uniformName, float value) {
        glUniform1f(uniforms.get(uniformName), value);
    }

    /**
     * Set a uniform to an float array value
     * @param uniformName which uniform
     * @param value the value to set it to
     */
    public void setUniform(String uniformName, float[] value) {
        glUniform2fv(uniforms.get(uniformName), value);
    }

}
