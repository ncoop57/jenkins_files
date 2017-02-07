package edu.uwf

class Utilities implements Serializable {
  def steps
  Utilities(steps) {this.steps = steps}
  def thing(args) {
    steps.echo "${args}"
  }
}
