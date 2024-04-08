import { useEffect, useState } from "react";
import WarehouseDAO from "../../repository/WarehouseDAO";
import {
  Box,
  FormControl,
  InputLabel,
  MenuItem,
  Pagination,
  Select,
  Stack,
  Typography,
} from "@mui/material";
import ProductCard from "./ProductCard";

const Products = () => {
  const [page, setPage] = useState(null);
  const [sortBy, setSortBy] = useState("name");
  const [sortDirection, setSortDirection] = useState("ASC");
  const [pageNumber, setPageNumber] = useState(0);

  const handleSortByChange = (event) => {
    setSortBy(event.target.value);
  };
  const handleSortDirectionChange = (event) => {
    setSortDirection(event.target.value);
  };
  const handlePageChange = (event, value) => {
    setPageNumber(value - 1);
  };

  useEffect(() => {
    WarehouseDAO.pageAndSort(pageNumber, 5, sortBy, sortDirection).then(
      (res) => {
        setPage(res.data);
      }
    );
  }, [pageNumber, sortBy, sortDirection]);
  if (!page) return <Typography variant="h1">Loading...</Typography>;
  return (
    <>
      <Box sx={{ display: "flex", justifyContent: "end", m: 1 }}>
        <FormControl sx={{ m: 1, minWidth: 120 }}>
          <InputLabel id="item-sorting-select">Sort By:</InputLabel>
          <Select
            labelId="item-sorting-select"
            id="item-sorting-select-helper"
            value={sortBy}
            label="Age"
            onChange={handleSortByChange}
          >
            <MenuItem value={"name"}>Name</MenuItem>
            <MenuItem value={"price"}>Price</MenuItem>
          </Select>
        </FormControl>
        <FormControl sx={{ m: 1, minWidth: 120 }}>
          <InputLabel id="item-sorting-direction-select">
            Sort Direction:
          </InputLabel>
          <Select
            labelId="item-sorting-direction-select"
            id="item-sorting-direction-select-helper"
            value={sortDirection}
            label="Sort Direction"
            onChange={handleSortDirectionChange}
          >
            <MenuItem value={"ASC"}>Ascending</MenuItem>
            <MenuItem value={"DESC"}>Descending</MenuItem>
          </Select>
        </FormControl>
      </Box>
      <Box
        sx={{
          display: "flex",
          flexWrap: "wrap",
          justifyContent: "space-around",
        }}
      >
        {page.content.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </Box>
      <Stack spacing={2}>
        <Pagination
          count={page.totalPages}
          page={pageNumber + 1}
          onChange={handlePageChange}
        />
      </Stack>
    </>
  );
};

export default Products;
