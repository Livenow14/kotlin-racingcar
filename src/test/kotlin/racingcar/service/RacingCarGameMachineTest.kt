package racingcar.service

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.inspectors.forAtLeastOne
import io.kotest.matchers.shouldBe
import racingcar.service.strategy.NumberNineGenerate
import racingcar.service.strategy.NumberZeroGenerate
import racingcar.service.strategy.RandomNumberZeroToNineGenerate

class RacingCarGameMachineTest : ShouldSpec({

    context("게임을 진행한다.") {
        should("일반적인 게임을 진행한다.") {
            val sut = RacingCarGameMachine(RandomNumberZeroToNineGenerate())
            val expectedCarCount = 3
            val expectedTryCount = 5

            val actual = sut.play(expectedCarCount, expectedTryCount)

            actual.size shouldBe expectedTryCount
        }

        should("전체 이동되는 게임을 진행한다.") {
            val sut = RacingCarGameMachine(NumberNineGenerate())
            val expectedCarCount = 3
            val expectedTryCount = 5

            val actual = sut.play(expectedCarCount, expectedTryCount)

            actual.size shouldBe expectedTryCount
            actual.forAtLeastOne { result ->
                result.cars.forAtLeastOne { car ->
                    car.position shouldBe 6
                }
            }
        }

        should("전체 멈춰있는 게임을 진행한다.") {
            val sut = RacingCarGameMachine(NumberZeroGenerate())
            val expectedCarCount = 3
            val expectedTryCount = 5

            val actual = sut.play(expectedCarCount, expectedTryCount)

            actual.size shouldBe expectedTryCount
            actual.forAtLeastOne { result ->
                result.cars.forAtLeastOne { car ->
                    car.position shouldBe 1
                }
            }
        }
    }
})
