export const mixin = (obj) => {
     const base = {
      data () {
        return {
          $adminConfig: {
            autoClose: false,
            openerCallBack: ''
          }
        }
      },
      computed: {
        // userPermissions () {
        //   return this.$store.getters.permissions
        // }
      },
      methods: {
        registryVm () {
          window.$currentVm = this
        },
        pageOver () {
          const timer = arguments[arguments.length - 1] &&
          typeof arguments[arguments.length - 1] === 'number' ? arguments[arguments.length - 1] : 300
          const opener = window.opener
          if (!opener) return
          const {autoClose, openerCallBack} = this.$data.$adminConfig
          if (openerCallBack &&
            typeof opener.$currentVm[openerCallBack] === 'function') {
            opener.$currentVm[openerCallBack](...arguments)
          }
          if (autoClose === true) {
            setTimeout(() => {
              window.close()
            }, timer)
          }
        },
        callNewPage (url, options = {}) {
          if (!options.autoClose) options.autoClose = true
          let path = /\/$/.test(url) ? url : `${url}/`
          const target = /^(http|https):\/\//.test(path) ? url
            : `/#${url}`
          let queryStr = ''
          for (let key in options) {
            queryStr += queryStr === '' ? `?${key}=${options[key]}` : `&${key}=${options[key]}`
          }
          window.open(`${target}${queryStr}`)
        }
      },
      mounted () {
        this.$data.$adminConfig.autoClose = !!this.$route.query.autoClose
        this.$data.$adminConfig.openerCallBack = this.$route.query.callback
        this.registryVm()
      },
      beforeRouteUpdate () {
        this.registryVm()
      }
    }
    if (obj.mixins) {
      obj.mixins.push(base)
    } else {
      obj.mixins = [base]
    }
    return obj
  }
