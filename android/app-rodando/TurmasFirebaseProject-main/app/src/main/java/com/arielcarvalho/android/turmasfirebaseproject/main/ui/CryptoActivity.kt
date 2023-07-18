package com.arielcarvalho.android.turmasfirebaseproject.main.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.arielcarvalho.android.turmasfirebaseproject.databinding.FragmentCryptoBinding
import java.io.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

class CryptoActivity : AppCompatActivity() {
    val TAG = "Arquivos"


    private lateinit var binding: FragmentCryptoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCryptoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setup()
    }

    private fun setup() {
        setupViews()
        setupClickListeners()
    }

    private fun setupViews() {
        setTitle("Crypto")
    }

    private fun setupClickListeners() {
        binding.apply {
            btnGravar.setOnClickListener {
                onGravarClick()
            }

            btnLer.setOnClickListener {
                onLerClick()
            }

            fabFoto.setOnClickListener {
                dispatchTakePictureIntent()
            }
            btnVoltarDeCrypto.setOnClickListener {
                //TODO()
            }
        }
    }

    private fun onLerClick() {

        val nomeArquivo = binding.inputNomeArquivo.text.toString()

        if (verifyFileExists(nomeArquivo)) {

            if (!nomeArquivo.isNullOrBlank()) {
                val texto = readCipherTextFile(nomeArquivo)

                binding.inputConteudoArquivo.setText(texto)

                foto = readCipherPhotoFile(nomeArquivo)

                if(foto == null){
                    Log.i(TAG, "Foto é nula")
                } else {
                    Log.i(TAG, "Foto _NÃO_ é nula")

                }

                foto?.let { setFotoToView(it) }

            }
        }


    }

    private fun onGravarClick() {

        val nomeArquivo = binding.inputNomeArquivo.text.toString()
        val conteudo = binding.inputConteudoArquivo.text.toString()

        if (!nomeArquivo.isNullOrBlank()) {
            deleteIfExists(nomeArquivo)
            saveCipherTextFile(nomeArquivo, conteudo)
            if (foto != null) {
                saveCipherPhotoFile(nomeArquivo, foto!!)
            }
        }


    }

    // Tirar Fotos:
    // https://developer.android.com/training/camera/photobasics

    val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            //takePictureIntent.resolveActivity(packageManager)?.also {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            //}
        }
    }

    var foto: Bitmap? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null) {
                foto = data.extras?.get("data") as Bitmap
                setFotoToView(foto!!)
            }

        }
    }

    fun setFotoToView(foto: Bitmap) {
        binding.ivFoto.setImageBitmap(foto)
    }

    fun verifyFileExists(nomeArquivo: String): Boolean {
        val arquivoTexto = File(filesDir, "${nomeArquivo}.txt")
        val arquivoFoto = File(filesDir, "${nomeArquivo}.fig")

        val txtExists = arquivoTexto.exists()
        val figExists = arquivoFoto.exists()

        if (!txtExists) {
            toast("${nomeArquivo}.txt não existe")
        }
        if (!figExists) {
            toast("${nomeArquivo}.fig não existe")
        }

        return (txtExists && figExists)
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun deleteIfExists(nomeArquivo: String) {

        val arquivoTexto = File(filesDir, "${nomeArquivo}.txt")
        val arquivoFoto = File(filesDir, "${nomeArquivo}.fig")

        val txtExists = arquivoTexto.exists()
        val figExists = arquivoFoto.exists()

        if (txtExists) {
            arquivoTexto.delete()
        }
        if (figExists) {
            arquivoFoto.delete()
        }


    }


    fun cipherBitmap(original: Bitmap): ByteArray {
        val criptografador = Criptografador()
        var chave = criptografador.getSecretKey()
        if (chave != null) {
            Cipher.getInstance("AES/CBC/PKCS7Padding").run {
                init(Cipher.ENCRYPT_MODE, chave)

                val stream = ByteArrayOutputStream()
                original.compress(Bitmap.CompressFormat.PNG, 90, stream)
                val imageByteArray = stream.toByteArray()

                var valorCripto = doFinal(imageByteArray)
                var ivCripto = ByteArray(16)
                iv.copyInto(ivCripto, 0, 0, 16)
                return ivCripto + valorCripto
            }
        } else return byteArrayOf()

    }

    fun cipher(original: String): ByteArray {

        val criptografador = Criptografador()

        var chave = criptografador.getSecretKey()

        if (chave != null) {
            Cipher.getInstance("AES/CBC/PKCS7Padding").run {
                init(Cipher.ENCRYPT_MODE, chave)
                var valorCripto = doFinal(original.toByteArray())
                var ivCripto = ByteArray(16)
                iv.copyInto(ivCripto, 0, 0, 16)
                return ivCripto + valorCripto
            }
        } else return byteArrayOf()
    }

    // bytearray  de tamanho 54
    // 0 - 15 -> iv
    // 16 - 53 -> mensagem criptografada

    fun decipher(cripto: ByteArray): String {
        val criptografador = Criptografador()
        var chave = criptografador.getSecretKey()

        if (chave != null) {
            Cipher.getInstance("AES/CBC/PKCS7Padding").run {
                var ivCripto = ByteArray(16)
                var valorCripto = ByteArray(cripto.size - 16)
                cripto.copyInto(ivCripto, 0, 0, 16)
                cripto.copyInto(valorCripto, 0, 16, cripto.size)
                val ivParams = IvParameterSpec(ivCripto)
                init(Cipher.DECRYPT_MODE, chave, ivParams)
                return String(doFinal(valorCripto))
            }
        } else return ""
    }

    fun decipherBitmap( cripto: ByteArray): Bitmap? {
        val criptografador = Criptografador()
        var chave = criptografador.getSecretKey()

        if (chave != null) {
            Cipher.getInstance("AES/CBC/PKCS7Padding").run {
                var ivCripto = ByteArray(16)
                var valorCripto = ByteArray(cripto.size - 16)
                cripto.copyInto(ivCripto, 0, 0, 16)
                cripto.copyInto(valorCripto, 0, 16, cripto.size)
                val ivParams = IvParameterSpec(ivCripto)
                init(Cipher.DECRYPT_MODE, chave, ivParams)

                val arrayInputStream = ByteArrayInputStream(doFinal(valorCripto) as ByteArray?)
                val bitmap = BitmapFactory.decodeStream(arrayInputStream)
                return bitmap
            }
        } else return null
    }

    fun saveCipherTextFile(nomeArquivo: String, conteudo: String) {

        val arquivoTexto = File(filesDir, "${nomeArquivo}.txt")

        val fos = arquivoTexto.outputStream()

        val conteudoCifrado = cipher(conteudo)

        fos.apply {
            write(conteudoCifrado)
            close()
        }

    }

    fun saveCipherPhotoFile(nomeArquivo: String, conteudo: Bitmap) {

        val arquivoFoto = File(filesDir, "${nomeArquivo}.fig")
        val fos = arquivoFoto.outputStream()

        val conteudoCifrado = cipherBitmap(conteudo)

        fos.apply {
            write(conteudoCifrado)
            close()
        }


    }

    fun readCipherTextFile(nomeArquivo: String): String {
        val arquivoFoto = File(filesDir, "${nomeArquivo}.txt")

        val fis = arquivoFoto.inputStream()

        val conteudoBytes = fis.readBytes()

        val conteudoDecifrado = decipher(conteudoBytes)

        return conteudoDecifrado
    }

    fun readCipherPhotoFile(nomeArquivo: String): Bitmap? {
        val arquivoFoto = File(filesDir, "${nomeArquivo}.fig")

        val fis = arquivoFoto.inputStream()

        val conteudoBytes = fis.readBytes()

        val conteudoDecifrado = decipherBitmap(conteudoBytes)

        return conteudoDecifrado
    }


}
