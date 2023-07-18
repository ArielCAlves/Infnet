package com.arielcarvalho.android.turmasfirebaseproject.models

data class AlunoNaTurma(
    val nomeEstudante: String = "",
    val nro_inscricao: String = "",
    val notaTp1: String = "",
    val notaTp3: String = "",
    val notaAT: String = "",
    val frequencia: Double = 0.0
)