(ns garden.units-test
  (:use clojure.test
       garden.units)
  (:import garden.types.CSSUnit))

(deftest test-unit-arthimetic
  (let [μm (make-unit-fn :μm)
        μm+ (make-unit-adder :μm)
        μm- (make-unit-subtractor :μm)
        μm* (make-unit-multiplier :μm)
        μm-div (make-unit-divider :μm)]
    (testing "addition"
      (is (= (μm 0) (μm+)))
      (is (= (μm 2) (μm+ 1 1))))
    (testing "subtraction"
      (is (= (μm -1) (μm- 1)))
      (is (= (μm 2) (μm- 4 2))))
    (testing "multiplication"
      (is (= (μm 1) (μm*)))
      (is (= (μm 2) (μm* 1 2))))
    (testing "division"
      (is (= (μm 1) (μm-div 1)))
      (is (= (μm 1/2) (μm-div 2)))
      (is (thrown? ArithmeticException (μm-div 2 0))))))

(deftest test-px
  (testing "px checking"
    (is (px? (px 0)))
    (is (not (px? 1))))
  (testing "px addition"
    (is (= (px 2) (px+ 1 1))))
  (testing "px subtraction"
    (is (= (px 2) (px- 4 2))))
  (testing "px multiplication"
    (is (= (px 2) (px* 1 2))))
  (testing "px division"
    (is (= (px 2) (px-div 4 2)))
    (is (thrown? ArithmeticException (px-div 2 0))))
  (testing "px conversion"
    (is (= (px 1) (px (px 1))))
    (is (= (px 37.795275591) (px (cm 1))))
    (is (= (px 16) (px (pc 1))))
    (is (= (px 3.7795275591) (px (mm 1))))
    (is (= (px 1.3333333333) (px (pt 1))))
    (is (thrown? IllegalArgumentException (px (deg 1))))
    (is (thrown? IllegalArgumentException (px (grad 1))))
    (is (thrown? IllegalArgumentException (px (rad 1))))
    (is (thrown? IllegalArgumentException (px (turn 1))))
    (is (thrown? IllegalArgumentException (px (s 1))))
    (is (thrown? IllegalArgumentException (px (ms 1))))
    (is (thrown? IllegalArgumentException (px (Hz 1))))
    (is (thrown? IllegalArgumentException (px (kHz 1))))))

(deftest unit-utils
  (testing "read-unit"
    (is (= (px 1) (read-unit (px 1))))
    (is (= (px 1.3) (read-unit (px 1.3))))
    (is (= (cm 1) (read-unit "1cm")))
    (is (= (mm 1) (read-unit "1mm")))
    (is (= (in 1) (read-unit "1in")))
    (is (= (px 1) (read-unit "1px")))
    (is (= (pt 1) (read-unit "1pt")))
    (is (= (pc 1) (read-unit "1pc")))
    (is (= (percent 1) (read-unit "1%")))
    (is (= (em 1) (read-unit "1em")))
    (is (= (ex 1) (read-unit "1ex")))
    (is (= (ch 1) (read-unit "1ch")))
    (is (= (CSSUnit. 1 :rem) (read-unit "1rem")))
    (is (= (vw 1) (read-unit "1vw")))
    (is (= (vh 1) (read-unit "1vh")))
    (is (= (vmin 1) (read-unit "1vmin")))
    (is (= (vmax 1) (read-unit "1vmax")))
    (is (= (deg 1) (read-unit "1deg")))
    (is (= (grad 1) (read-unit "1grad")))
    (is (= (rad 1) (read-unit "1rad")))
    (is (= (turn 1) (read-unit "1turn")))
    (is (= (ms 1) (read-unit "1ms")))
    (is (= (s 1) (read-unit "1s")))
    (is (= (kHz 1) (read-unit "1kHz")))
    (is (= (Hz 1) (read-unit "1Hz")))
    (is (= (dpi 1) (read-unit "1dpi")))
    (is (= (dpcm 1) (read-unit "1dpcm")))
    (is (= (dppx 1) (read-unit "1dppx")))))

