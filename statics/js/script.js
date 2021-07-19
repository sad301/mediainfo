function _() {

  this.index = {};
  this.index.init = () => {
    console.log('we\'re at index');
  };

}

_.q = function () {
  return new _();
};
