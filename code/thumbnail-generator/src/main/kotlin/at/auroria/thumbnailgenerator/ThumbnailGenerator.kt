package at.auroria.thumbnailgenerator

import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.geometry.Positions
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.*
import javax.imageio.ImageIO

class ThumbnailGenerator {
    companion object {
        fun toThumbnail(inputStream: InputStream, contentType: String, width: Int, height: Int): InputStream {
            val bufferedImage = ImageIO.read(inputStream)

            val i = if (bufferedImage.width > bufferedImage.height) bufferedImage.height else bufferedImage.width
            val os = ByteArrayOutputStream()

            Thumbnails.fromImages(Collections.singleton(bufferedImage))
                    .size(width, height)
                    .sourceRegion(Positions.CENTER, i, i)
                    .outputFormat(contentType.split("/").last())
                    .toOutputStream(os)

            return ByteArrayInputStream(os.toByteArray())
        }
    }
}