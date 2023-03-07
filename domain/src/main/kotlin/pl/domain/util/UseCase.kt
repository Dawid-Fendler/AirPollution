package pl.domain.util

interface UseCase<IN : Any, OUT : Any> {
    fun execute(input: IN): OUT
}
