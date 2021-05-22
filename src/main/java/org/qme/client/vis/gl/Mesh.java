package org.qme.client.vis.gl;

import org.lwjgl.system.MemoryUtil;
import org.qme.client.vis.tex.TextureManager;
import org.qme.io.Logger;
import org.qme.io.Severity;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;

/**
 * A mesh with texture coordinates, position coordinates, etc. This contains the
 * data OpenGL actually uses to render stuff.
 */
public final class Mesh {

    /**
     * A bunch of IDs! vaoID is the ID of all of the data's "linking point", so
     * to speak. All of the xxxVboID are the IDs are various sets of data GL
     * uses.
     */
    private final int vaoId,
            posVboId, idxVboId, texVboId,
            vertexCount
                    ;

    /**
     * Set up all of the arrays into buffers and such.
     * @param positions the locations that things get drawn at
     * @param indices the indices of the position buffer to draw, so we don't
     *                repeat data.
     * @param texPositions the texture coordinates to use.
     */
    public Mesh(float[] positions, int[] indices, float[] texPositions) {

        // The buffers we'll place the data in
        FloatBuffer verticesBuffer, texBuffer;
        IntBuffer indexBuffer;

        vertexCount = indices.length;

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        // Vertices
        posVboId = glGenBuffers();
        verticesBuffer = MemoryUtil.memAllocFloat(positions.length);
        verticesBuffer.put(positions).flip();
        glBindBuffer(GL_ARRAY_BUFFER, posVboId);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        MemoryUtil.memFree(verticesBuffer);

        // Indices
        idxVboId = glGenBuffers();
        indexBuffer = MemoryUtil.memAllocInt(indices.length);
        indexBuffer.put(indices).flip();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
        MemoryUtil.memFree(indexBuffer);

        // Texture coordinates
        texVboId = glGenBuffers();
        texBuffer = MemoryUtil.memAllocFloat(texPositions.length);
        texBuffer.put(texPositions).flip();
        glBindBuffer(GL_ARRAY_BUFFER, texVboId);
        glBufferData(GL_ARRAY_BUFFER, texBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        // We're not placing data into the vao anymore
        glBindVertexArray(0);

    }

    /**
     * Draw this mesh using the texture given.
     * TODO: optimize this so we don't load and unload the same texture 3721897482394 times.
     * @param textureName which texture
     */
    public void render(String textureName) {

        // Activate first texture unit
        glActiveTexture(GL_TEXTURE0);
        // Bind the texture (this line is expensive to run)
        Integer texID = TextureManager.getTexture(textureName);
        if (texID == null) {
            Logger.log("Attempted to load nonexistent texture! Texture: " + textureName, Severity.FATAL);
        }
        glBindTexture(GL_TEXTURE_2D, texID);

        // Draw the mesh
        glBindVertexArray(vaoId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);

        // Clear everything for next mesh
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

    }

    /**
     * Clear all of the mesh data. Because OpenGL deals with this, Java does not
     * garbage collect so please call this manually.
     */
    public void delete() {

        glDisableVertexAttribArray(0);

        // Clear the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // Flush all data
        glDeleteBuffers(posVboId);
        glDeleteBuffers(idxVboId);
        glDeleteBuffers(texVboId);

        // Clear the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);

    }

}
