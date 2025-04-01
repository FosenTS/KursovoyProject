@GetMapping("/chairs")
public String listChairs(Model model) {
    List<Chair> chairs = chairService.getAllChairs();
    // Make sure chairs are loaded with their faculty relationships
    model.addAttribute("chairs", chairs);
    return "chairs";
} 