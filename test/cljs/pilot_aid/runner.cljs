(ns pilot-aid.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [pilot-aid.core-test]))

(doo-tests 'pilot-aid.core-test)
