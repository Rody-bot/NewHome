// 查询列表数据
const getItemsetPage = (params) => {
  return $axios({
    url: '/itemset/page',
    method: 'get',
    params
  })
}

// 删除数据接口
const deleteItemset = (ids) => {
  return $axios({
    url: '/itemset',
    method: 'delete',
    params: { ids }
  })
}

// 修改数据接口
const editItemset = (params) => {
  return $axios({
    url: '/itemset',
    method: 'put',
    data: { ...params }
  })
}

// 新增数据接口
const addItemset = (params) => {
  return $axios({
    url: '/itemset',
    method: 'post',
    data: { ...params }
  })
}

// 查询详情接口
const queryItemsetById = (id) => {
  return $axios({
    url: `/itemset/${id}`,
    method: 'get'
  })
}

// 批量起售禁售
const itemsetStatusByStatus = (params) => {
  return $axios({
    url: `/itemset/status/${params.status}`,
    method: 'post',
    params: { ids: params.ids }
  })
}
